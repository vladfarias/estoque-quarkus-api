package io.github.vladfarias.service;

import io.github.vladfarias.dto.EstoqueResponseDTO;
import io.github.vladfarias.dto.MovimentacaoEstoqueRequestDTO;
import io.github.vladfarias.entity.EstoqueEntity;
import io.github.vladfarias.entity.MovimentacaoEstoqueEntity;
import io.github.vladfarias.entity.ProdutoEntity;
import io.github.vladfarias.exception.EstoqueInsuficienteException;
import io.github.vladfarias.exception.EstoqueNaoEncontradoException;
import io.github.vladfarias.exception.ProdutoNaoEncontradoException;
import io.github.vladfarias.exception.ReservaInsuficienteException;
import io.github.vladfarias.repository.EstoqueRepository;
import io.github.vladfarias.repository.MovimentacaoEstoqueRepository;
import io.github.vladfarias.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;
    private final MovimentacaoEstoqueRepository movimentacaoRepository;

    @Inject
    public EstoqueService(
            EstoqueRepository estoqueRepository,
            ProdutoRepository produtoRepository,
            MovimentacaoEstoqueRepository movimentacaoRepository
    ) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public List<EstoqueResponseDTO> listarTodos() {
        return estoqueRepository.listAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public EstoqueResponseDTO buscarPorProdutoId(Long produtoId) {
        EstoqueEntity estoque = estoqueRepository
                .buscarPorProdutoId(produtoId)
                .orElseThrow(
                        () -> new EstoqueNaoEncontradoException(
                                produtoId
                        )
                );

        return converterParaResponseDTO(estoque);
    }

    public EstoqueResponseDTO buscarPorSku(String sku) {
        EstoqueEntity estoque = estoqueRepository
                .buscarPorSku(sku)
                .orElseThrow(
                        () -> new EstoqueNaoEncontradoException(sku)
                );

        return converterParaResponseDTO(estoque);
    }

    @Transactional
    public EstoqueResponseDTO registrarEntrada(
            MovimentacaoEstoqueRequestDTO requestDTO
    ) {
        ProdutoEntity produto = buscarProdutoPorSku(
                requestDTO.getSku()
        );

        EstoqueEntity estoque = estoqueRepository
                .buscarPorSkuComBloqueio(requestDTO.getSku())
                .orElseGet(
                        () -> criarEstoqueVazio(produto)
                );

        estoque.setQuantidadeDisponivel(
                estoque.getQuantidadeDisponivel()
                        + requestDTO.getQuantidade()
        );

        estoque.setDataAtualizacao(LocalDateTime.now());

        registrarMovimentacao(
                produto,
                "ENTRADA",
                requestDTO
        );

        return converterParaResponseDTO(estoque);
    }

    @Transactional
    public EstoqueResponseDTO registrarSaida(
            MovimentacaoEstoqueRequestDTO requestDTO
    ) {
        EstoqueEntity estoque = buscarEstoqueComBloqueio(
                requestDTO.getSku()
        );

        validarEstoqueDisponivel(
                estoque,
                requestDTO.getQuantidade()
        );

        estoque.setQuantidadeDisponivel(
                estoque.getQuantidadeDisponivel()
                        - requestDTO.getQuantidade()
        );

        estoque.setDataAtualizacao(LocalDateTime.now());

        registrarMovimentacao(
                estoque.getProduto(),
                "SAIDA",
                requestDTO
        );

        return converterParaResponseDTO(estoque);
    }

    @Transactional
    public EstoqueResponseDTO reservar(
            MovimentacaoEstoqueRequestDTO requestDTO
    ) {
        EstoqueEntity estoque = buscarEstoqueComBloqueio(
                requestDTO.getSku()
        );

        validarEstoqueDisponivel(
                estoque,
                requestDTO.getQuantidade()
        );

        estoque.setQuantidadeDisponivel(
                estoque.getQuantidadeDisponivel()
                        - requestDTO.getQuantidade()
        );

        estoque.setQuantidadeReservada(
                estoque.getQuantidadeReservada()
                        + requestDTO.getQuantidade()
        );

        estoque.setDataAtualizacao(LocalDateTime.now());

        registrarMovimentacao(
                estoque.getProduto(),
                "RESERVA",
                requestDTO
        );

        return converterParaResponseDTO(estoque);
    }

    @Transactional
    public EstoqueResponseDTO cancelarReserva(
            MovimentacaoEstoqueRequestDTO requestDTO
    ) {
        EstoqueEntity estoque = buscarEstoqueComBloqueio(
                requestDTO.getSku()
        );

        validarQuantidadeReservada(
                estoque,
                requestDTO.getQuantidade()
        );

        estoque.setQuantidadeReservada(
                estoque.getQuantidadeReservada()
                        - requestDTO.getQuantidade()
        );

        estoque.setQuantidadeDisponivel(
                estoque.getQuantidadeDisponivel()
                        + requestDTO.getQuantidade()
        );

        estoque.setDataAtualizacao(LocalDateTime.now());

        registrarMovimentacao(
                estoque.getProduto(),
                "CANCELAMENTO",
                requestDTO
        );

        return converterParaResponseDTO(estoque);
    }

    private ProdutoEntity buscarProdutoPorSku(String sku) {
        return produtoRepository
                .buscarPorSku(sku)
                .orElseThrow(
                        () -> new ProdutoNaoEncontradoException(sku)
                );
    }

    private EstoqueEntity buscarEstoqueComBloqueio(String sku) {
        return estoqueRepository
                .buscarPorSkuComBloqueio(sku)
                .orElseThrow(
                        () -> new EstoqueNaoEncontradoException(sku)
                );
    }

    private EstoqueEntity criarEstoqueVazio(
            ProdutoEntity produto
    ) {
        EstoqueEntity estoque = new EstoqueEntity(
                produto,
                0,
                0,
                LocalDateTime.now()
        );

        estoqueRepository.persist(estoque);

        return estoque;
    }

    private void validarEstoqueDisponivel(
            EstoqueEntity estoque,
            Integer quantidade
    ) {
        if (estoque.getQuantidadeDisponivel() < quantidade) {
            throw new EstoqueInsuficienteException(
                    estoque.getProduto().getSku(),
                    quantidade,
                    estoque.getQuantidadeDisponivel()
            );
        }
    }

    private void validarQuantidadeReservada(
            EstoqueEntity estoque,
            Integer quantidade
    ) {
        if (estoque.getQuantidadeReservada() < quantidade) {
            throw new ReservaInsuficienteException(
                    estoque.getProduto().getSku(),
                    quantidade,
                    estoque.getQuantidadeReservada()
            );
        }
    }

    private void registrarMovimentacao(
            ProdutoEntity produto,
            String tipo,
            MovimentacaoEstoqueRequestDTO requestDTO
    ) {
        MovimentacaoEstoqueEntity movimentacao =
                new MovimentacaoEstoqueEntity(
                        produto,
                        tipo,
                        requestDTO.getQuantidade(),
                        requestDTO.getReferencia(),
                        requestDTO.getObservacao(),
                        LocalDateTime.now()
                );

        movimentacaoRepository.persist(movimentacao);
    }

    private EstoqueResponseDTO converterParaResponseDTO(
            EstoqueEntity estoque
    ) {
        EstoqueResponseDTO responseDTO =
                new EstoqueResponseDTO();

        responseDTO.setProdutoId(
                estoque.getProduto().getId()
        );

        responseDTO.setSku(
                estoque.getProduto().getSku()
        );

        responseDTO.setNome(
                estoque.getProduto().getNome()
        );

        responseDTO.setQuantidadeDisponivel(
                estoque.getQuantidadeDisponivel()
        );

        responseDTO.setQuantidadeReservada(
                estoque.getQuantidadeReservada()
        );

        responseDTO.setDataAtualizacao(
                estoque.getDataAtualizacao()
        );

        return responseDTO;
    }
}
package io.github.vladfarias.service;

import io.github.vladfarias.dto.ProdutoRequestDTO;
import io.github.vladfarias.dto.ProdutoResponseDTO;
import io.github.vladfarias.entity.ProdutoEntity;
import io.github.vladfarias.exception.ProdutoNaoEncontradoException;
import io.github.vladfarias.repository.ProdutoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Inject
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.listAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        ProdutoEntity produto = buscarEntityPorId(id);

        return converterParaResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO requestDTO) {
        ProdutoEntity produto = converterParaEntity(requestDTO);

        produtoRepository.persist(produto);

        return converterParaResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO atualizar(
            Long id,
            ProdutoRequestDTO requestDTO
    ) {
        ProdutoEntity produto = buscarEntityPorId(id);

        produto.setSku(requestDTO.getSku());
        produto.setNome(requestDTO.getNome());
        produto.setAtivo(requestDTO.getAtivo());

        return converterParaResponseDTO(produto);
    }

    @Transactional
    public void excluir(Long id) {
        ProdutoEntity produto = buscarEntityPorId(id);

        produtoRepository.delete(produto);
    }

    private ProdutoEntity buscarEntityPorId(Long id) {
        return produtoRepository.findByIdOptional(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    private ProdutoEntity converterParaEntity(
            ProdutoRequestDTO requestDTO
    ) {
        return new ProdutoEntity(
                requestDTO.getSku(),
                requestDTO.getNome(),
                requestDTO.getAtivo()
        );
    }

    private ProdutoResponseDTO converterParaResponseDTO(
            ProdutoEntity produto
    ) {
        ProdutoResponseDTO responseDTO = new ProdutoResponseDTO();

        responseDTO.setId(produto.getId());
        responseDTO.setSku(produto.getSku());
        responseDTO.setNome(produto.getNome());
        responseDTO.setAtivo(produto.getAtivo());

        return responseDTO;
    }
}
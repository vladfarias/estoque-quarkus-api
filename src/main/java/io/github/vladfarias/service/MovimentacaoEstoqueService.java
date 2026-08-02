package io.github.vladfarias.service;

import io.github.vladfarias.dto.MovimentacaoEstoqueResponseDTO;
import io.github.vladfarias.entity.MovimentacaoEstoqueEntity;
import io.github.vladfarias.repository.MovimentacaoEstoqueRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoRepository;

    @Inject
    public MovimentacaoEstoqueService(
            MovimentacaoEstoqueRepository movimentacaoRepository
    ) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public List<MovimentacaoEstoqueResponseDTO> listarTodas() {
        return movimentacaoRepository
                .listarTodasOrdenadas()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public List<MovimentacaoEstoqueResponseDTO> buscarPorProdutoId(
            Long produtoId
    ) {
        return movimentacaoRepository
                .buscarPorProdutoId(produtoId)
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public List<MovimentacaoEstoqueResponseDTO> buscarPorReferencia(
            String referencia
    ) {
        return movimentacaoRepository
                .buscarPorReferencia(referencia)
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    private MovimentacaoEstoqueResponseDTO converterParaResponseDTO(
            MovimentacaoEstoqueEntity movimentacao
    ) {
        MovimentacaoEstoqueResponseDTO responseDTO =
                new MovimentacaoEstoqueResponseDTO();

        responseDTO.setId(movimentacao.getId());

        responseDTO.setProdutoId(
                movimentacao.getProduto().getId()
        );

        responseDTO.setSku(
                movimentacao.getProduto().getSku()
        );

        responseDTO.setTipo(
                movimentacao.getTipo()
        );

        responseDTO.setQuantidade(
                movimentacao.getQuantidade()
        );

        responseDTO.setReferencia(
                movimentacao.getReferencia()
        );

        responseDTO.setObservacao(
                movimentacao.getObservacao()
        );

        responseDTO.setDataMovimentacao(
                movimentacao.getDataMovimentacao()
        );

        return responseDTO;
    }
}
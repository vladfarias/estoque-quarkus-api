package io.github.vladfarias.repository;

import io.github.vladfarias.entity.MovimentacaoEstoqueEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MovimentacaoEstoqueRepository
        implements PanacheRepository<MovimentacaoEstoqueEntity> {

    public List<MovimentacaoEstoqueEntity> listarTodasOrdenadas() {
        return find("order by dataMovimentacao desc").list();
    }

    public List<MovimentacaoEstoqueEntity> buscarPorProdutoId(
            Long produtoId
    ) {
        return find(
                "produto.id = ?1 order by dataMovimentacao desc",
                produtoId
        ).list();
    }

    public List<MovimentacaoEstoqueEntity> buscarPorReferencia(
            String referencia
    ) {
        return find(
                "referencia = ?1 order by dataMovimentacao desc",
                referencia
        ).list();
    }
}
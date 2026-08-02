package io.github.vladfarias.repository;

import io.github.vladfarias.entity.EstoqueEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.LockModeType;

import java.util.Optional;

@ApplicationScoped
public class EstoqueRepository
        implements PanacheRepository<EstoqueEntity> {

    public Optional<EstoqueEntity> buscarPorProdutoId(Long produtoId) {
        return find("produto.id", produtoId)
                .firstResultOptional();
    }

    public Optional<EstoqueEntity> buscarPorSku(String sku) {
        return find("produto.sku", sku)
                .firstResultOptional();
    }

    public Optional<EstoqueEntity> buscarPorSkuComBloqueio(String sku) {
        return find("produto.sku", sku)
                .withLock(LockModeType.PESSIMISTIC_WRITE)
                .firstResultOptional();
    }
}
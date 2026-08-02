package io.github.vladfarias.repository;

import io.github.vladfarias.entity.ProdutoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class ProdutoRepository
        implements PanacheRepository<ProdutoEntity> {

    public Optional<ProdutoEntity> buscarPorSku(String sku) {
        return find("sku", sku).firstResultOptional();
    }
}
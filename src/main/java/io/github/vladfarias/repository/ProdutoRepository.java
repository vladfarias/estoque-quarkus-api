package io.github.vladfarias.repository;

import io.github.vladfarias.entity.ProdutoEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository
        implements PanacheRepository<ProdutoEntity> {

}
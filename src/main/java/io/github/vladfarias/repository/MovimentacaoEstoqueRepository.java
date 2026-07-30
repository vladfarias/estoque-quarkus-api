package io.github.vladfarias.repository;

import io.github.vladfarias.entity.MovimentacaoEstoqueEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovimentacaoEstoqueRepository
        implements PanacheRepository<MovimentacaoEstoqueEntity> {

}
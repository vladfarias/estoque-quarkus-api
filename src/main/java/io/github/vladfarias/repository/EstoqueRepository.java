package io.github.vladfarias.repository;

import io.github.vladfarias.entity.EstoqueEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EstoqueRepository
        implements PanacheRepository<EstoqueEntity> {

}
package io.github.vladfarias.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "estoque")
@Getter
@Setter
@NoArgsConstructor
public class EstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "produto_id",
            nullable = false,
            unique = true
    )
    private ProdutoEntity produto;

    @Column(name = "quantidade_disponivel", nullable = false)
    private Integer quantidadeDisponivel;

    @Column(name = "quantidade_reservada", nullable = false)
    private Integer quantidadeReservada;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDateTime dataAtualizacao;

    public EstoqueEntity(
            ProdutoEntity produto,
            Integer quantidadeDisponivel,
            Integer quantidadeReservada,
            LocalDateTime dataAtualizacao
    ) {
        this.produto = produto;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.quantidadeReservada = quantidadeReservada;
        this.dataAtualizacao = dataAtualizacao;
    }
}
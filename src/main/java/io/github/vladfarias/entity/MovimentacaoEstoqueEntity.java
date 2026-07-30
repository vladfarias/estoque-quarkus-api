package io.github.vladfarias.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacao_estoque")
@Getter
@Setter
@NoArgsConstructor
public class MovimentacaoEstoqueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoEntity produto;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    public MovimentacaoEstoqueEntity(
            ProdutoEntity produto,
            String tipo,
            Integer quantidade,
            String referencia,
            String observacao,
            LocalDateTime dataMovimentacao
    ) {
        this.produto = produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.referencia = referencia;
        this.observacao = observacao;
        this.dataMovimentacao = dataMovimentacao;
    }
}
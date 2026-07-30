package io.github.vladfarias.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EstoqueResponseDTO {

    private Long produtoId;
    private String sku;
    private String nome;

    private Integer quantidadeDisponivel;
    private Integer quantidadeReservada;

    private LocalDateTime dataAtualizacao;
}
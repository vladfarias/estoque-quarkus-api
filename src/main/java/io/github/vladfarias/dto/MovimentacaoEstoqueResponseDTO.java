package io.github.vladfarias.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MovimentacaoEstoqueResponseDTO {

    private Long id;

    private Long produtoId;
    private String sku;

    private String tipo;
    private Integer quantidade;

    private String referencia;
    private String observacao;

    private LocalDateTime dataMovimentacao;
}
package io.github.vladfarias.dto;

import io.github.vladfarias.enums.TipoMovimentacao;
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

    private TipoMovimentacao tipo;

    private Integer quantidade;

    private String referencia;
    private String observacao;

    private LocalDateTime dataMovimentacao;
}
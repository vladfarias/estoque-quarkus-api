package io.github.vladfarias.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovimentacaoEstoqueRequestDTO {

    @NotBlank
    private String sku;

    @NotNull
    @Positive
    private Integer quantidade;

    private String referencia;

    private String observacao;
}
package io.github.vladfarias.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoRequestDTO {

    @NotBlank
    private String sku;

    @NotBlank
    private String nome;

    @NotNull
    private Boolean ativo;
}
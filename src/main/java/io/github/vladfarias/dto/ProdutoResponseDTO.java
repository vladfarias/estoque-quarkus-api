package io.github.vladfarias.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoResponseDTO {

    private Long id;
    private String sku;
    private String nome;
    private Boolean ativo;
}
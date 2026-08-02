package io.github.vladfarias.exception;

public class EstoqueNaoEncontradoException
        extends RuntimeException {

    public EstoqueNaoEncontradoException(String sku) {
        super("Estoque não encontrado para o SKU: " + sku);
    }

    public EstoqueNaoEncontradoException(Long produtoId) {
        super(
                "Estoque não encontrado para o produto de ID: "
                        + produtoId
        );
    }
}
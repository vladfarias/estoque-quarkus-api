package io.github.vladfarias.exception;

public class ProdutoNaoEncontradoException
        extends RuntimeException {

    public ProdutoNaoEncontradoException(Long id) {
        super("Produto não encontrado com o ID: " + id);
    }

    public ProdutoNaoEncontradoException(String sku) {
        super("Produto não encontrado com o SKU: " + sku);
    }
}
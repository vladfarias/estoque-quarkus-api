package io.github.vladfarias.exception;

public class EstoqueInsuficienteException
        extends RuntimeException {

    public EstoqueInsuficienteException(
            String sku,
            Integer solicitado,
            Integer disponivel
    ) {
        super(
                "Estoque insuficiente para o SKU "
                        + sku
                        + ". Quantidade solicitada: "
                        + solicitado
                        + ". Quantidade disponível: "
                        + disponivel
        );
    }
}
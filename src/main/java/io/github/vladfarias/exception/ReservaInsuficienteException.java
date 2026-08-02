package io.github.vladfarias.exception;

public class ReservaInsuficienteException
        extends RuntimeException {

    public ReservaInsuficienteException(
            String sku,
            Integer solicitado,
            Integer reservado
    ) {
        super(
                "Quantidade reservada insuficiente para o SKU "
                        + sku
                        + ". Quantidade solicitada para cancelamento: "
                        + solicitado
                        + ". Quantidade reservada: "
                        + reservado
        );
    }
}
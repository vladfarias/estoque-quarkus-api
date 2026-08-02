package io.github.vladfarias.exception;

import io.github.vladfarias.dto.ApiErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class EstoqueInsuficienteExceptionMapper
        implements ExceptionMapper<EstoqueInsuficienteException> {

    @Override
    public Response toResponse(
            EstoqueInsuficienteException exception
    ) {
        ApiErrorDTO error = new ApiErrorDTO(
                LocalDateTime.now(),
                Response.Status.CONFLICT.getStatusCode(),
                Response.Status.CONFLICT.getReasonPhrase(),
                exception.getMessage()
        );

        return Response
                .status(Response.Status.CONFLICT)
                .entity(error)
                .build();
    }
}
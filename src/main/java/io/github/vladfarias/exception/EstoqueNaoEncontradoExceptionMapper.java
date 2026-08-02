package io.github.vladfarias.exception;

import io.github.vladfarias.dto.ApiErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class EstoqueNaoEncontradoExceptionMapper
        implements ExceptionMapper<EstoqueNaoEncontradoException> {

    @Override
    public Response toResponse(
            EstoqueNaoEncontradoException exception
    ) {
        ApiErrorDTO error = new ApiErrorDTO(
                LocalDateTime.now(),
                Response.Status.NOT_FOUND.getStatusCode(),
                Response.Status.NOT_FOUND.getReasonPhrase(),
                exception.getMessage()
        );

        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}
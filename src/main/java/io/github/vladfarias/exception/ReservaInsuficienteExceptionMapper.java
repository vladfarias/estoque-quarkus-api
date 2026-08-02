package io.github.vladfarias.exception;

import io.github.vladfarias.dto.ApiErrorDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

@Provider
public class ReservaInsuficienteExceptionMapper
        implements ExceptionMapper<ReservaInsuficienteException> {

    @Override
    public Response toResponse(
            ReservaInsuficienteException exception
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
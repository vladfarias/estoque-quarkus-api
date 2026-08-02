package io.github.vladfarias.controller;

import io.github.vladfarias.dto.MovimentacaoEstoqueResponseDTO;
import io.github.vladfarias.service.MovimentacaoEstoqueService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/movimentacoes")
@Produces(MediaType.APPLICATION_JSON)
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoService;

    @Inject
    public MovimentacaoEstoqueController(
            MovimentacaoEstoqueService movimentacaoService
    ) {
        this.movimentacaoService = movimentacaoService;
    }

    @GET
    public Response listarTodas() {
        List<MovimentacaoEstoqueResponseDTO> movimentacoes =
                movimentacaoService.listarTodas();

        return Response.ok(movimentacoes).build();
    }

    @GET
    @Path("/produtos/{produtoId}")
    public Response buscarPorProdutoId(
            @PathParam("produtoId") Long produtoId
    ) {
        List<MovimentacaoEstoqueResponseDTO> movimentacoes =
                movimentacaoService.buscarPorProdutoId(produtoId);

        return Response.ok(movimentacoes).build();
    }

    @GET
    @Path("/referencias/{referencia}")
    public Response buscarPorReferencia(
            @PathParam("referencia") String referencia
    ) {
        List<MovimentacaoEstoqueResponseDTO> movimentacoes =
                movimentacaoService.buscarPorReferencia(referencia);

        return Response.ok(movimentacoes).build();
    }
}
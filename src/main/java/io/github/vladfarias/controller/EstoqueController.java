package io.github.vladfarias.controller;

import io.github.vladfarias.dto.EstoqueResponseDTO;
import io.github.vladfarias.dto.MovimentacaoEstoqueRequestDTO;
import io.github.vladfarias.service.EstoqueService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/estoques")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstoqueController {

    private final EstoqueService estoqueService;

    @Inject
    public EstoqueController(
            EstoqueService estoqueService
    ) {
        this.estoqueService = estoqueService;
    }

    @GET
    public Response listarTodos() {
        List<EstoqueResponseDTO> estoques =
                estoqueService.listarTodos();

        return Response.ok(estoques).build();
    }

    @GET
    @Path("/produtos/{produtoId}")
    public Response buscarPorProdutoId(
            @PathParam("produtoId") Long produtoId
    ) {
        EstoqueResponseDTO estoque =
                estoqueService.buscarPorProdutoId(
                        produtoId
                );

        return Response.ok(estoque).build();
    }

    @GET
    @Path("/sku/{sku}")
    public Response buscarPorSku(
            @PathParam("sku") String sku
    ) {
        EstoqueResponseDTO estoque =
                estoqueService.buscarPorSku(sku);

        return Response.ok(estoque).build();
    }

    @POST
    @Path("/entradas")
    public Response registrarEntrada(
            @Valid MovimentacaoEstoqueRequestDTO requestDTO
    ) {
        EstoqueResponseDTO estoque =
                estoqueService.registrarEntrada(requestDTO);

        return Response.ok(estoque).build();
    }

    @POST
    @Path("/saidas")
    public Response registrarSaida(
            @Valid MovimentacaoEstoqueRequestDTO requestDTO
    ) {
        EstoqueResponseDTO estoque =
                estoqueService.registrarSaida(requestDTO);

        return Response.ok(estoque).build();
    }

    @POST
    @Path("/reservas")
    public Response reservar(
            @Valid MovimentacaoEstoqueRequestDTO requestDTO
    ) {
        EstoqueResponseDTO estoque =
                estoqueService.reservar(requestDTO);

        return Response.ok(estoque).build();
    }

    @POST
    @Path("/cancelamentos")
    public Response cancelarReserva(
            @Valid MovimentacaoEstoqueRequestDTO requestDTO
    ) {
        EstoqueResponseDTO estoque =
                estoqueService.cancelarReserva(requestDTO);

        return Response.ok(estoque).build();
    }
}
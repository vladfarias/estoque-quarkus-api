package io.github.vladfarias.controller;

import io.github.vladfarias.dto.ProdutoRequestDTO;
import io.github.vladfarias.dto.ProdutoResponseDTO;
import io.github.vladfarias.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {

    private final ProdutoService produtoService;

    @Inject
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GET
    public Response listarTodos() {
        List<ProdutoResponseDTO> produtos =
                produtoService.listarTodos();

        return Response.ok(produtos).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        ProdutoResponseDTO produto =
                produtoService.buscarPorId(id);

        return Response.ok(produto).build();
    }

    @POST
    public Response criar(@Valid ProdutoRequestDTO requestDTO) {
        ProdutoResponseDTO produtoCriado =
                produtoService.criar(requestDTO);

        URI location = URI.create(
                "/produtos/" + produtoCriado.getId()
        );

        return Response
                .created(location)
                .entity(produtoCriado)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(
            @PathParam("id") Long id,
            @Valid ProdutoRequestDTO requestDTO
    ) {
        ProdutoResponseDTO produtoAtualizado =
                produtoService.atualizar(id, requestDTO);

        return Response.ok(produtoAtualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluir(@PathParam("id") Long id) {
        produtoService.excluir(id);

        return Response.noContent().build();
    }
}
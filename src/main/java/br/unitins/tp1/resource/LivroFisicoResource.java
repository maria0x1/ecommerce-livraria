package br.unitins.tp1.resource;

import br.unitins.tp1.dto.LivroFisicoDTO;
import br.unitins.tp1.dto.LivroFisicoResponseDTO;
import br.unitins.tp1.service.LivroFisicoService;
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

@Path("/livros/fisicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivroFisicoResource {

    @Inject
    LivroFisicoService livroFisicoService;

    @POST
    public LivroFisicoResponseDTO inserir(@Valid LivroFisicoDTO dto) {
        return livroFisicoService.create(dto);
    }

    @GET
    @Path("/{id}")
    public LivroFisicoResponseDTO buscarPorId(@PathParam("id") Long id) {
        return livroFisicoService.findById(id);
    }

    @PUT
    @Path("/{id}")
    public LivroFisicoResponseDTO atualizar(
            @PathParam("id") Long id,
            @Valid LivroFisicoDTO dto) {
        return livroFisicoService.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void excluir(@PathParam("id") Long id) {
        livroFisicoService.delete(id);
    }
}

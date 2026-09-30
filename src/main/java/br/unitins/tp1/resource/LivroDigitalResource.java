package br.unitins.tp1.resource;

import br.unitins.tp1.dto.LivroDigitalDTO;
import br.unitins.tp1.dto.LivroDigitalResponseDTO;
import br.unitins.tp1.service.LivroDigitalService;
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

@Path("/livros/digitais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivroDigitalResource {

    @Inject
    LivroDigitalService livroDigitalService;

    @POST
    public LivroDigitalResponseDTO inserir(@Valid LivroDigitalDTO dto) {
        return livroDigitalService.create(dto);
    }

    @GET
    @Path("/{id}")
    public LivroDigitalResponseDTO buscarPorId(@PathParam("id") Long id) {
        return livroDigitalService.findById(id);
    }

    @PUT
    @Path("/{id}")
    public LivroDigitalResponseDTO atualizar(
            @PathParam("id") Long id,
            @Valid LivroDigitalDTO dto) {
        return livroDigitalService.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public void excluir(@PathParam("id") Long id) {
        livroDigitalService.delete(id);
    }
}

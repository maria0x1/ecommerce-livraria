package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.dto.CategoriaDTO;
import br.unitins.tp1.dto.CategoriaResponseDTO;
import br.unitins.tp1.service.CategoriaService;
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
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/categorias")
@Tag(name = "Categorias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    CategoriaService categoriaService;

    @POST
    @Operation(summary = "Criar categoria")
    @APIResponse(responseCode = "201", description = "Categoria criada",
            content = @Content(schema = @Schema(implementation = CategoriaResponseDTO.class)))
    public Response create(@Valid CategoriaDTO dto) {
        CategoriaResponseDTO categoria = categoriaService.create(dto);
        return Response.status(Status.CREATED).entity(categoria).build();
    }

    @GET
    @Operation(summary = "Listar categorias")
    @APIResponse(responseCode = "200", description = "Categorias",
            content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = CategoriaResponseDTO.class)))
    public Response findAll() {
        List<CategoriaResponseDTO> categorias = categoriaService.findAll();
        return Response.ok(categorias).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar categoria")
    @APIResponse(responseCode = "200", description = "Categoria encontrada",
            content = @Content(schema = @Schema(implementation = CategoriaResponseDTO.class)))
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(categoriaService.findById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar categoria")
    @APIResponse(responseCode = "200", description = "Categoria atualizada",
            content = @Content(schema = @Schema(implementation = CategoriaResponseDTO.class)))
    public Response update(@PathParam("id") Long id, @Valid CategoriaDTO dto) {
        return Response.ok(categoriaService.update(id, dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Remover categoria")
    @APIResponse(responseCode = "204", description = "Categoria removida")
    public Response delete(@PathParam("id") Long id) {
        categoriaService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}

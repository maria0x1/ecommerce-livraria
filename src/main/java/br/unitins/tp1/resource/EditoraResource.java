package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.dto.EditoraDTO;
import br.unitins.tp1.dto.EditoraResponseDTO;
import br.unitins.tp1.service.EditoraService;
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

@Path("/editoras")
@Tag(name = "Editoras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EditoraResource {

    @Inject
    EditoraService editoraService;

    @POST
    @Operation(summary = "Criar editora")
    @APIResponse(responseCode = "201", description = "Editora criada",
            content = @Content(schema = @Schema(implementation = EditoraResponseDTO.class)))
    public Response create(@Valid EditoraDTO dto) {
        EditoraResponseDTO editora = editoraService.create(dto);
        return Response.status(Status.CREATED).entity(editora).build();
    }

    @GET
    @Operation(summary = "Listar editoras")
    @APIResponse(responseCode = "200", description = "Editoras",
            content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = EditoraResponseDTO.class)))
    public Response findAll() {
        List<EditoraResponseDTO> editoras = editoraService.findAll();
        return Response.ok(editoras).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar editora")
    @APIResponse(responseCode = "200", description = "Editora encontrada",
            content = @Content(schema = @Schema(implementation = EditoraResponseDTO.class)))
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(editoraService.findById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar editora")
    @APIResponse(responseCode = "200", description = "Editora atualizada",
            content = @Content(schema = @Schema(implementation = EditoraResponseDTO.class)))
    public Response update(@PathParam("id") Long id, @Valid EditoraDTO dto) {
        return Response.ok(editoraService.update(id, dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Remover editora")
    @APIResponse(responseCode = "204", description = "Editora removida")
    public Response delete(@PathParam("id") Long id) {
        editoraService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}

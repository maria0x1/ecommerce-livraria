package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.dto.UsuarioAdminDTO;
import br.unitins.tp1.dto.UsuarioAdminResponseDTO;
import br.unitins.tp1.service.UsuarioAdminService;
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

@Path("/usuarios-admin")
@Tag(name = "Usuários administrativos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioAdminResource {

    @Inject
    UsuarioAdminService usuarioAdminService;

    @POST
    @Operation(summary = "Criar usuário administrativo")
    @APIResponse(responseCode = "201", description = "Usuário criado",
            content = @Content(schema = @Schema(implementation = UsuarioAdminResponseDTO.class)))
    public Response create(@Valid UsuarioAdminDTO dto) {
        UsuarioAdminResponseDTO usuario = usuarioAdminService.create(dto);
        return Response.status(Status.CREATED).entity(usuario).build();
    }

    @GET
    @Operation(summary = "Listar usuários administrativos")
    @APIResponse(responseCode = "200", description = "Usuários",
            content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = UsuarioAdminResponseDTO.class)))
    public Response findAll() {
        List<UsuarioAdminResponseDTO> usuarios = usuarioAdminService.findAll();
        return Response.ok(usuarios).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar usuário administrativo")
    @APIResponse(responseCode = "200", description = "Usuário encontrado",
            content = @Content(schema = @Schema(implementation = UsuarioAdminResponseDTO.class)))
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(usuarioAdminService.findById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar usuário administrativo")
    @APIResponse(responseCode = "200", description = "Usuário atualizado",
            content = @Content(schema = @Schema(implementation = UsuarioAdminResponseDTO.class)))
    public Response update(@PathParam("id") Long id, @Valid UsuarioAdminDTO dto) {
        return Response.ok(usuarioAdminService.update(id, dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Remover usuário administrativo")
    @APIResponse(responseCode = "204", description = "Usuário removido")
    public Response delete(@PathParam("id") Long id) {
        usuarioAdminService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}

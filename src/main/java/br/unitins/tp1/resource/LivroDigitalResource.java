package br.unitins.tp1.resource;

import br.unitins.tp1.dto.LivroDigitalDTO;
import br.unitins.tp1.dto.LivroDigitalResponseDTO;
import br.unitins.tp1.dto.DescontoDTO;
import br.unitins.tp1.service.LivroDigitalService;
import java.util.List;
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

@Path("/livros/digitais")
@Tag(name = "Livros digitais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivroDigitalResource {

    @Inject
    LivroDigitalService livroDigitalService;

    @GET
    @Operation(summary = "Listar livros digitais")
    @APIResponse(responseCode = "200", description = "Livros digitais",
            content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = LivroDigitalResponseDTO.class)))
    public List<LivroDigitalResponseDTO> listarTodos() {
        return livroDigitalService.findAll();
    }

    @POST
    @Operation(summary = "Criar livro digital")
    @APIResponse(responseCode = "201", description = "Livro digital criado",
            content = @Content(schema = @Schema(implementation = LivroDigitalResponseDTO.class)))
    public Response inserir(@Valid LivroDigitalDTO dto) {
        return Response.status(Status.CREATED).entity(livroDigitalService.create(dto)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar livro digital")
    public LivroDigitalResponseDTO buscarPorId(@PathParam("id") Long id) {
        return livroDigitalService.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar livro digital")
    public LivroDigitalResponseDTO atualizar(
            @PathParam("id") Long id,
            @Valid LivroDigitalDTO dto) {
        return livroDigitalService.update(id, dto);
    }

    @POST
    @Path("/{id}/descontos")
    @Operation(summary = "Aplicar desconto ao livro digital")
    public LivroDigitalResponseDTO aplicarDesconto(
            @PathParam("id") Long id,
            @Valid DescontoDTO dto) {
        return livroDigitalService.aplicarDesconto(id, dto.percentual());
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Remover livro digital")
    @APIResponse(responseCode = "204", description = "Livro digital removido")
    public Response excluir(@PathParam("id") Long id) {
        livroDigitalService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}

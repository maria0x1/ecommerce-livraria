package br.unitins.tp1.resource;

import br.unitins.tp1.dto.LivroFisicoDTO;
import br.unitins.tp1.dto.LivroFisicoResponseDTO;
import br.unitins.tp1.dto.BaixaEstoqueDTO;
import br.unitins.tp1.dto.DescontoDTO;
import br.unitins.tp1.service.LivroFisicoService;
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
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/livros/fisicos")
@Tag(name = "Livros físicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivroFisicoResource {

    @Inject
    LivroFisicoService livroFisicoService;

    @GET
    @Operation(summary = "Listar livros físicos")
    @APIResponse(responseCode = "200", description = "Livros físicos",
            content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = LivroFisicoResponseDTO.class)))
    public List<LivroFisicoResponseDTO> listarTodos() {
        return livroFisicoService.findAll();
    }

    @POST
    @Operation(summary = "Criar livro físico")
    @APIResponse(responseCode = "201", description = "Livro físico criado",
            content = @Content(schema = @Schema(implementation = LivroFisicoResponseDTO.class)))
    public Response inserir(@Valid LivroFisicoDTO dto) {
        return Response.status(Status.CREATED).entity(livroFisicoService.create(dto)).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Consultar livro físico")
    public LivroFisicoResponseDTO buscarPorId(@PathParam("id") Long id) {
        return livroFisicoService.findById(id);
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Atualizar livro físico")
    public LivroFisicoResponseDTO atualizar(
            @PathParam("id") Long id,
            @Valid LivroFisicoDTO dto) {
        return livroFisicoService.update(id, dto);
    }

    @POST
    @Path("/{id}/baixas-estoque")
    @Operation(summary = "Baixar unidades do estoque")
    public LivroFisicoResponseDTO baixarEstoque(
            @PathParam("id") Long id,
            @Valid BaixaEstoqueDTO dto) {
        return livroFisicoService.baixarEstoque(id, dto.quantidade());
    }

    @POST
    @Path("/{id}/descontos")
    @Operation(summary = "Aplicar desconto ao livro físico")
    public LivroFisicoResponseDTO aplicarDesconto(
            @PathParam("id") Long id,
            @Valid DescontoDTO dto) {
        return livroFisicoService.aplicarDesconto(id, dto.percentual());
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Remover livro físico")
    @APIResponse(responseCode = "204", description = "Livro físico removido")
    public Response excluir(@PathParam("id") Long id) {
        livroFisicoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }
}

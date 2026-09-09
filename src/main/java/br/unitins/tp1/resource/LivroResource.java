package br.unitins.tp1.resource;

import java.util.List;

import br.unitins.tp1.model.Livro;
import br.unitins.tp1.service.LivroService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/livros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class LivroResource {
    @Inject
    LivroService livroService;

    @GET
    public List<Livro> listarTudo() {
        return livroService.findAll();
    }

    @GET
    @Path("/buscar/{id}")

    public Livro buscarPorId(@PathParam("id") Long id) {
        return livroService.findByID(id);
    }

    @GET
    @Path("/buscar/titulo/{titulo}")
    public List<Livro> buscarPorTitulo(@PathParam("titulo") String titulo) {
        return livroService.findByTitulo(titulo);
    }

    @GET
    @Path("/buscar/autor/{autor}")
    public List<Livro> buscarPorAutor(@PathParam("autor") String autor) {
        return livroService.findByAutor(autor);
    }

    @GET
    @Path("/buscar/editora/{editora}")
    public List<Livro> buscarPorEditora(@PathParam("editora") String editora) {
        return livroService.findByEditora(editora);
    }

    @POST
    public Livro inserir(Livro livro) {
        return livroService.create(livro);
    } 

    @PUT
    @Path("atualizar/{id}")
    public void atualizar(@PathParam("id") Long id, Livro livro) {
       livroService.update(id, livro);
    } 

    @DELETE
    @Path("deletar/{id}")
    public void excluir(@PathParam("id") Long id) {
        livroService.delete(id);
    }

}

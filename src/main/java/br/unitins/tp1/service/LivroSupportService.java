package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.LivroDTO;
import br.unitins.tp1.model.Editora;
import br.unitins.tp1.model.IsbnUtils;
import br.unitins.tp1.model.Livro;
import br.unitins.tp1.repository.EditoraRepository;
import br.unitins.tp1.repository.LivroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class LivroSupportService {

    @Inject
    EditoraRepository editoraRepository;

    @Inject
    LivroRepository livroRepository;

    @Inject
    LivroCategoriaService livroCategoriaService;

    public void preencherDadosComuns(
            Livro livro,
            String titulo,
            String autor,
            String isbn,
            Double preco,
            java.time.LocalDate dataPublicacao,
            Long idEditora,
            List<Long> idsCategorias) {
        String isbnNormalizado = IsbnUtils.normalize(isbn);
        Long idAtual = livro.getId();
        Livro isbnExistente = livroRepository.find("isbn", isbnNormalizado).firstResult();
        if (isbnExistente != null && !isbnExistente.getId().equals(idAtual)) {
            throw new BadRequestException("O ISBN informado já está cadastrado.");
        }

        Editora editora = editoraRepository.findById(idEditora);
        if (editora == null) {
            throw new NotFoundException();
        }

        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setIsbn(isbnNormalizado);
        livro.setPreco(preco);
        livro.setDataPublicacao(dataPublicacao);
        livro.setEditora(editora);
        livro.setCategorias(livroCategoriaService.findCategorias(idsCategorias));
    }

    public void preencherDadosComuns(Livro livro, LivroDTO dto) {
        preencherDadosComuns(
                livro,
                dto.titulo(),
                dto.autor(),
                dto.isbn(),
                dto.preco(),
                dto.dataPublicacao(),
                dto.idEditora(),
                dto.idsCategorias());
    }
}

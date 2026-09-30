package br.unitins.tp1.service;

import java.util.List;
import br.unitins.tp1.dto.LivroDTO;
import br.unitins.tp1.dto.LivroResponseDTO;
import br.unitins.tp1.model.Editora;
import br.unitins.tp1.model.Livro;
import br.unitins.tp1.repository.EditoraRepository;
import br.unitins.tp1.repository.LivroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class LivroServiceImpl implements LivroService {

    @Inject
    LivroRepository livroRepository;

    @Inject
    EditoraRepository editoraRepository;

    @Override
    @Transactional
    public LivroResponseDTO create(@Valid LivroDTO dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());

        // Busca a editora pelo ID recebido no DTO
        Editora editora = editoraRepository.findById(dto.idEditora());
        livro.setEditora(editora);

        livroRepository.persist(livro);
        return LivroResponseDTO.valueOf(livro);
    }

    @Override
    @Transactional
    public LivroResponseDTO update(Long id, @Valid LivroDTO dto) {
        Livro livro = livroRepository.findById(id);
        if (livro == null) {
            return null;
        }

        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());

        Editora editora = editoraRepository.findById(dto.idEditora());
        livro.setEditora(editora);

        return LivroResponseDTO.valueOf(livro);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        livroRepository.deleteById(id);
    }

    @Override
    public LivroResponseDTO findById(Long id) {
        Livro livro = livroRepository.findById(id);
        return LivroResponseDTO.valueOf(livro);
    }

    @Override
    public List<LivroResponseDTO> findAll() {
        return livroRepository.listAll()
                .stream()
                .map(LivroResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<LivroResponseDTO> findByTitulo(String titulo) {
        return livroRepository.find("UPPER(titulo) LIKE ?1", "%" + titulo.toUpperCase() + "%")
                .list()
                .stream()
                .map(LivroResponseDTO::valueOf)
                .toList();
    }

    @Override
    public List<LivroResponseDTO> findByAutor(String autor) {
        return livroRepository.find("UPPER(autor) LIKE ?1", "%" + autor.toUpperCase() + "%")
                .list()
                .stream()
                .map(LivroResponseDTO::valueOf)
                .toList();
    }
}
package br.unitins.tp1.service;

import java.util.List;
import br.unitins.tp1.dto.LivroResponseDTO;
import br.unitins.tp1.model.Livro;
import br.unitins.tp1.repository.LivroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LivroServiceImpl implements LivroService {

    @Inject
    LivroRepository livroRepository;

    @Override
    @Transactional
    public void delete(Long id) {
        livroRepository.deleteById(id);
    }

    @Override
    public LivroResponseDTO findByID(Long id) {
        Livro livro = livroRepository.findById(id);
        return LivroResponseDTO.fromEntity(livro);
    }

    @Override
    public List<LivroResponseDTO> findAll() {
        return livroRepository.listAll()
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<LivroResponseDTO> findByTitulo(String titulo) {
        return livroRepository.find("UPPER(titulo) LIKE ?1", "%" + titulo.toUpperCase() + "%")
                .list()
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<LivroResponseDTO> findByAutor(String autor) {
        return livroRepository.find("UPPER(autor) LIKE ?1", "%" + autor.toUpperCase() + "%")
                .list()
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<LivroResponseDTO> findByEditora(String editora) {
        return livroRepository.findByEditora(editora)
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }
}
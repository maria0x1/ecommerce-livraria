package br.unitins.tp1.service;

import java.util.List;
import br.unitins.tp1.dto.LivroResponseDTO;
import br.unitins.tp1.model.Livro;
import br.unitins.tp1.repository.LivroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class LivroServiceImpl implements LivroService {

    @Inject
    LivroRepository livroRepository;

    @Override
    @Transactional
    public void delete(Long id) {
        if (!livroRepository.deleteById(id)) {
            throw new NotFoundException();
        }
    }

    @Override
    @Transactional
    public LivroResponseDTO findByID(Long id) {
        Livro livro = livroRepository.findById(id);
        if (livro == null) {
            throw new NotFoundException();
        }
        return LivroResponseDTO.fromEntity(livro);
    }

    @Override
    @Transactional
    public List<LivroResponseDTO> findAll() {
        return livroRepository.listAll()
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public List<LivroResponseDTO> findByTitulo(String titulo) {
        return livroRepository.find("UPPER(titulo) LIKE ?1", "%" + titulo.toUpperCase() + "%")
                .list()
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public List<LivroResponseDTO> findByAutor(String autor) {
        return livroRepository.find("UPPER(autor) LIKE ?1", "%" + autor.toUpperCase() + "%")
                .list()
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public List<LivroResponseDTO> findByEditora(String editora) {
        return livroRepository.findByEditora(editora)
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }
}
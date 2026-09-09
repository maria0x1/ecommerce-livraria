package br.unitins.tp1.service;

import java.util.List;


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
    public Livro create(Livro livro) {
        livroRepository.persist(livro);
        return livro;
    }

    @Override
    public void update(Long id, Livro livro) {
        Livro livroEstoque = livroRepository.findById(id);
        if (livroEstoque == null) { 
            throw new RuntimeException("livro não encontrado");
        }
        livroEstoque.setTitulo(livro.getTitulo());
        livroEstoque.setAutor(livro.getAutor());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        livroRepository.deleteById(id);
    }

    @Override
    public Livro findByID(Long id) {
        return livroRepository.findById(id);
    }

    @Override
    public List<Livro> findByTitulo(String titulo) {
        return livroRepository.findByTitulo(titulo);
    }

    @Override
    public List<Livro> findAll() {
        return livroRepository.listAll();
    }

    @Override
    public List<Livro> findByAutor(String autor) {
        return livroRepository.findByAutor(autor);
    }

    @Override
    public List<Livro> findByEditora(String editora) {
        return livroRepository.findByEditora(editora);
    }
    
}

package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.model.Livro;

public interface LivroService {
    Livro create(Livro livro);
    void update(Long id, Livro livro);
    void delete(Long id);
    Livro findByID(Long id);
    List<Livro> findByTitulo(String titulo);
    List<Livro> findByAutor(String autor);
    List<Livro> findByEditora(String editora);
    List<Livro> findAll();
}

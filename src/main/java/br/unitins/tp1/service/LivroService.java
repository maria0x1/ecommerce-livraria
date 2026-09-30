package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.*;
import br.unitins.tp1.model.Livro;
import jakarta.validation.Valid;

public interface LivroService {
    LivroDTOResponse create(@Valid Livro livro);
    LivroDTOResponse update(Long id,@Valid Livro livro);
    void delete(Long id);
    LivroDTOResponse findByID(Long id);
    List<LivroDTOResponse> findAll();
    List<LivroDTOResponse> findByTitulo(String titulo);
    List<LivroDTOResponse> findByAutor(String autor);   
    List<LivroDTOResponse> findByEditora(String editora);
}

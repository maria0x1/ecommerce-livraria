package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.LivroResponseDTO;

public interface LivroService {
    void delete(Long id);
    LivroResponseDTO findByID(Long id);
    List<LivroResponseDTO> findAll();
    List<LivroResponseDTO> findByTitulo(String titulo);
    List<LivroResponseDTO> findByAutor(String autor);
    List<LivroResponseDTO> findByEditora(String editora);
}

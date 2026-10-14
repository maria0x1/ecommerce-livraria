package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.CategoriaDTO;
import br.unitins.tp1.dto.CategoriaResponseDTO;

public interface CategoriaService {
    CategoriaResponseDTO create(CategoriaDTO dto);

    CategoriaResponseDTO update(Long id, CategoriaDTO dto);

    List<CategoriaResponseDTO> findAll();

    CategoriaResponseDTO findById(Long id);

    void delete(Long id);
}

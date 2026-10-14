package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.EditoraDTO;
import br.unitins.tp1.dto.EditoraResponseDTO;

public interface EditoraService {
    EditoraResponseDTO create(EditoraDTO dto);

    EditoraResponseDTO update(Long id, EditoraDTO dto);

    List<EditoraResponseDTO> findAll();

    EditoraResponseDTO findById(Long id);

    void delete(Long id);
}

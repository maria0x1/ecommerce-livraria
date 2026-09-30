package br.unitins.tp1.service;

import br.unitins.tp1.dto.LivroFisicoDTO;
import br.unitins.tp1.dto.LivroFisicoResponseDTO;
import jakarta.validation.Valid;

public interface LivroFisicoService {
    LivroFisicoResponseDTO create(@Valid LivroFisicoDTO dto);
    LivroFisicoResponseDTO update(Long id, @Valid LivroFisicoDTO dto);
    LivroFisicoResponseDTO findById(Long id);
    void delete(Long id);
}

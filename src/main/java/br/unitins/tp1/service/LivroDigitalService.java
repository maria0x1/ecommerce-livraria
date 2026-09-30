package br.unitins.tp1.service;

import br.unitins.tp1.dto.LivroDigitalDTO;
import br.unitins.tp1.dto.LivroDigitalResponseDTO;
import jakarta.validation.Valid;

public interface LivroDigitalService {
    LivroDigitalResponseDTO create(@Valid LivroDigitalDTO dto);
    LivroDigitalResponseDTO update(Long id, @Valid LivroDigitalDTO dto);
    LivroDigitalResponseDTO findById(Long id);
    void delete(Long id);
}

package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.LivroDigitalDTO;
import br.unitins.tp1.dto.LivroDigitalResponseDTO;
import jakarta.validation.Valid;

public interface LivroDigitalService {
    LivroDigitalResponseDTO create(@Valid LivroDigitalDTO dto);
    LivroDigitalResponseDTO update(Long id, @Valid LivroDigitalDTO dto);
    List<LivroDigitalResponseDTO> findAll();
    LivroDigitalResponseDTO findById(Long id);
    LivroDigitalResponseDTO aplicarDesconto(Long id, Double percentual);
    void delete(Long id);
}

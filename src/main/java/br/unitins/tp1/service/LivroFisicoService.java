package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.LivroFisicoDTO;
import br.unitins.tp1.dto.LivroFisicoResponseDTO;
import jakarta.validation.Valid;

public interface LivroFisicoService {
    LivroFisicoResponseDTO create(@Valid LivroFisicoDTO dto);
    LivroFisicoResponseDTO update(Long id, @Valid LivroFisicoDTO dto);
    List<LivroFisicoResponseDTO> findAll();
    LivroFisicoResponseDTO findById(Long id);
    LivroFisicoResponseDTO baixarEstoque(Long id, Integer quantidade);
    LivroFisicoResponseDTO aplicarDesconto(Long id, Double percentual);
    void delete(Long id);
}

package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.UsuarioAdminDTO;
import br.unitins.tp1.dto.UsuarioAdminResponseDTO;

public interface UsuarioAdminService {
    UsuarioAdminResponseDTO create(UsuarioAdminDTO dto);

    UsuarioAdminResponseDTO update(Long id, UsuarioAdminDTO dto);

    List<UsuarioAdminResponseDTO> findAll();

    UsuarioAdminResponseDTO findById(Long id);

    boolean autenticar(String login, String senhaAberta);

    void delete(Long id);
}

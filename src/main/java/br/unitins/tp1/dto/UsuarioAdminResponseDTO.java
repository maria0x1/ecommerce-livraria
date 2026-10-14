package br.unitins.tp1.dto;

import java.time.LocalDateTime;

import br.unitins.tp1.model.UsuarioAdmin;

public record UsuarioAdminResponseDTO(
        Long id,
        String nomeCompleto,
        String login,
        LocalDateTime dataCriacao,
        PerfilAcessoResponseDTO perfilAcesso) {

    public static UsuarioAdminResponseDTO valueOf(UsuarioAdmin usuario) {
        return new UsuarioAdminResponseDTO(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getLogin(),
                usuario.getDataCriacao(),
                PerfilAcessoResponseDTO.valueOf(usuario.getPerfilAcesso()));
    }
}

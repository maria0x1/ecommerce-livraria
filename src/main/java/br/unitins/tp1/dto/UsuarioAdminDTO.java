package br.unitins.tp1.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioAdminDTO(
        @NotBlank(message = "O nome completo é obrigatório")
        String nomeCompleto,
        @NotBlank(message = "O login é obrigatório")
        String login,
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 12, max = 128, message = "A senha deve ter entre 12 e 128 caracteres")
        String senha,
        @NotNull(message = "O perfil de acesso é obrigatório")
        @Valid PerfilAcessoDTO perfilAcesso) {
}

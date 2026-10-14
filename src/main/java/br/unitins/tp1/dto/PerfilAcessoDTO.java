package br.unitins.tp1.dto;

import br.unitins.tp1.model.NivelAcesso;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record PerfilAcessoDTO(
        @NotNull(message = "O nível de acesso é obrigatório")
        @Schema(description = "Enum enviado por nome no JSON. IDs: ROOT=1, GERENTE=2, OPERACIONAL=3.")
        NivelAcesso nivel,
        String permissoesDetalhadas) {
}

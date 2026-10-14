package br.unitins.tp1.dto;

import br.unitins.tp1.model.NivelAcesso;
import br.unitins.tp1.model.PerfilAcesso;

public record PerfilAcessoResponseDTO(
        Long id,
        NivelAcesso nivel,
        String permissoesDetalhadas) {

    public static PerfilAcessoResponseDTO valueOf(PerfilAcesso perfilAcesso) {
        return new PerfilAcessoResponseDTO(
                perfilAcesso.getId(),
                perfilAcesso.getNivel(),
                perfilAcesso.getPermissoesDetalhadas());
    }
}

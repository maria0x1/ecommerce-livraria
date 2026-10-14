package br.unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfil_acesso")
public class PerfilAcesso extends DefaultEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelAcesso nivel;

    private String permissoesDetalhadas;

    public NivelAcesso getNivel() {
        return nivel;
    }

    public void setNivel(NivelAcesso nivel) {
        this.nivel = nivel;
    }

    public String getPermissoesDetalhadas() {
        return permissoesDetalhadas;
    }

    public void setPermissoesDetalhadas(String permissoesDetalhadas) {
        this.permissoesDetalhadas = permissoesDetalhadas;
    }
}

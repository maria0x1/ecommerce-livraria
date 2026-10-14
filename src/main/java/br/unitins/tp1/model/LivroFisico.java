package br.unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "livro_fisico")
public class LivroFisico extends Livro {
    @Positive
    private Double pesoKg;

    @PositiveOrZero
    private Integer quantidadeEstoque;

    @NotBlank
    @Column(length = 80)
    private String tipoCapa;
    
    public Double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(Double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getTipoCapa() {
        return tipoCapa;
    }

    public void setTipoCapa(String tipoCapa) {
        this.tipoCapa = tipoCapa;
    }

    public void baixarEstoque(Integer quantidade) {
        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade a baixar deve ser positiva.");
        }
        if (quantidadeEstoque == null || quantidadeEstoque < quantidade) {
            throw new IllegalStateException("Estoque insuficiente.");
        }
        quantidadeEstoque -= quantidade;
    }
}

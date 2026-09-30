package br.unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro_fisico")
public class LivroFisico extends Livro {
    private Double peso;
    private Integer estoqueDisponivel;
    
    public Double getPeso() {
        return peso;
    }
    public void setPeso(Double peso) {
        this.peso = peso;
    }
    public Integer getEstoqueDisponivel() {
        return estoqueDisponivel;
    }
    public void setEstoqueDisponivel(Integer estoqueDisponivel) {
        this.estoqueDisponivel = estoqueDisponivel;
    }

    
}

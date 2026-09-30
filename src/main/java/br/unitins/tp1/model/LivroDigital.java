package br.unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro_digital")
public class LivroDigital extends Livro {
    private String formato;
    private Double tamanhoArquivo;
    
    public String getFormato() {
        return formato;
    }
    public void setFormato(String formato) {
        this.formato = formato;
    }
    public Double getTamanhoArquivo() {
        return tamanhoArquivo;
    }
    public void setTamanhoArquivo(Double tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    } 
    
}

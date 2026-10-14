package br.unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "livro_digital")
public class LivroDigital extends Livro {
    @NotBlank
    private String formatoArquivo;

    @Positive
    private Double tamanhoMb;

    private String licencaDrm;
    
    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    public void setFormatoArquivo(String formatoArquivo) {
        this.formatoArquivo = formatoArquivo;
    }

    public Double getTamanhoMb() {
        return tamanhoMb;
    }
    public void setTamanhoMb(Double tamanhoMb) {
        this.tamanhoMb = tamanhoMb;
    }

    public String getLicencaDrm() {
        return licencaDrm;
    }

    public void setLicencaDrm(String licencaDrm) {
        this.licencaDrm = licencaDrm;
    }
    
}

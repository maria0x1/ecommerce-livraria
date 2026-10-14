package br.unitins.tp1.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Livro extends DefaultEntity {
    
    @NotBlank
    private String titulo;

    @NotBlank
    private String autor;

    @Column(length = 32, unique = true)
    private String isbn;

    @PositiveOrZero
    private Double preco;

    private LocalDate dataPublicacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_editora", nullable = false)
    private Editora editora;

    @ManyToMany
    @JoinTable(name = "livro_categoria",
            joinColumns = @JoinColumn(name = "id_livro"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categoria> categorias = new ArrayList<>();
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Editora getEditora() {
        return editora;
    }
    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void aplicarDesconto(Double percentual) {
        if (percentual == null || !Double.isFinite(percentual) || percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("O percentual de desconto deve estar entre 0 e 100.");
        }
        if (preco == null || !Double.isFinite(preco) || preco < 0) {
            throw new IllegalStateException("O livro deve ter um preço válido para aplicar desconto.");
        }

        BigDecimal fator = BigDecimal.ONE.subtract(
                BigDecimal.valueOf(percentual).movePointLeft(2));
        preco = BigDecimal.valueOf(preco)
                .multiply(fator)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}

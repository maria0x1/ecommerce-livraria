package br.unitins.tp1.dto;

import br.unitins.tp1.model.Categoria;
import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(
        @NotBlank(message = "O nome da categoria é obrigatório")
        String nome,
        String descricao,
        Boolean ativo) {

    public Categoria toEntity() {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        categoria.setDescricao(descricao);
        categoria.setAtivo(ativo == null ? Boolean.TRUE : ativo);
        return categoria;
    }
}

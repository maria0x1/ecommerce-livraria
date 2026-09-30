package br.unitins.tp1.dto;

import br.unitins.tp1.model.Livro;

public record LivroResponseDTO(
    String titulo,
    String autor,
    Long idEditora
) {
    public static LivroResponseDTO fromEntity(Livro livro) {
        return new LivroResponseDTO(
            livro.getTitulo(),
            livro.getAutor(),
            livro.getEditora().getId()
        );
    }
}

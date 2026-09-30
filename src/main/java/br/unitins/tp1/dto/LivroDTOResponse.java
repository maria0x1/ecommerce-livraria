package br.unitins.tp1.dto;

import br.unitins.tp1.model.Livro;

public record LivroDTOResponse(
    String titulo,
    String autor,
    Long idEditora
) {
    public static LivroDTOResponse fromEntity(Livro livro) {
        return new LivroDTOResponse(
            livro.getTitulo(),
            livro.getAutor(),
            livro.getEditora().getId()
        );
    }
}

package br.unitins.tp1.dto;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.model.Livro;

public record LivroResponseDTO(
    String titulo,
    String autor,
    String isbn,
    Double preco,
    LocalDate dataPublicacao,
    Long idEditora,
    List<CategoriaResponseDTO> categorias
) {
    public static LivroResponseDTO fromEntity(Livro livro) {
        return new LivroResponseDTO(
            livro.getTitulo(),
            livro.getAutor(),
            livro.getIsbn(),
            livro.getPreco(),
            livro.getDataPublicacao(),
            livro.getEditora().getId(),
            livro.getCategorias().stream().map(CategoriaResponseDTO::valueOf).toList()
        );
    }
}

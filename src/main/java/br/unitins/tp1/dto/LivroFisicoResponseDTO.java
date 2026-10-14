package br.unitins.tp1.dto;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.model.LivroFisico;

public record LivroFisicoResponseDTO(
    Long id,
    String titulo,
    String autor,
    String isbn,
    Double preco,
    LocalDate dataPublicacao,
    Long idEditora,
    Double pesoKg,
    Integer quantidadeEstoque,
    String tipoCapa,
    List<CategoriaResponseDTO> categorias
) {
    public static LivroFisicoResponseDTO fromEntity(LivroFisico livro) {
        return new LivroFisicoResponseDTO(
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getIsbn(),
            livro.getPreco(),
            livro.getDataPublicacao(),
            livro.getEditora().getId(),
            livro.getPesoKg(),
            livro.getQuantidadeEstoque(),
            livro.getTipoCapa(),
            livro.getCategorias().stream().map(CategoriaResponseDTO::valueOf).toList()
        );
    }
}

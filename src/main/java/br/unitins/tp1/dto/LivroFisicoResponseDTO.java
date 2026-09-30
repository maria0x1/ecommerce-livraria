package br.unitins.tp1.dto;

import br.unitins.tp1.model.LivroFisico;

public record LivroFisicoResponseDTO(
    Long id,
    String titulo,
    String autor,
    Long idEditora,
    Double peso,
    Integer estoqueDisponivel
) {
    public static LivroFisicoResponseDTO fromEntity(LivroFisico livro) {
        return new LivroFisicoResponseDTO(
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getEditora().getId(),
            livro.getPeso(),
            livro.getEstoqueDisponivel()
        );
    }
}

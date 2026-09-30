package br.unitins.tp1.dto;

import br.unitins.tp1.model.LivroDigital;

public record LivroDigitalResponseDTO(
    Long id,
    String titulo,
    String autor,
    Long idEditora,
    String formato,
    Double tamanhoArquivo
) {
    public static LivroDigitalResponseDTO fromEntity(LivroDigital livro) {
        return new LivroDigitalResponseDTO(
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getEditora().getId(),
            livro.getFormato(),
            livro.getTamanhoArquivo()
        );
    }
}

package br.unitins.tp1.dto;

import java.time.LocalDate;
import java.util.List;

import br.unitins.tp1.model.LivroDigital;

public record LivroDigitalResponseDTO(
    Long id,
    String titulo,
    String autor,
    String isbn,
    Double preco,
    LocalDate dataPublicacao,
    Long idEditora,
    String formatoArquivo,
    Double tamanhoMb,
    String licencaDrm,
    List<CategoriaResponseDTO> categorias
) {
    public static LivroDigitalResponseDTO fromEntity(LivroDigital livro) {
        return new LivroDigitalResponseDTO(
            livro.getId(),
            livro.getTitulo(),
            livro.getAutor(),
            livro.getIsbn(),
            livro.getPreco(),
            livro.getDataPublicacao(),
            livro.getEditora().getId(),
            livro.getFormatoArquivo(),
            livro.getTamanhoMb(),
            livro.getLicencaDrm(),
            livro.getCategorias().stream().map(CategoriaResponseDTO::valueOf).toList()
        );
    }
}

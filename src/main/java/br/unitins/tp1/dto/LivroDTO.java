package br.unitins.tp1.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import br.unitins.tp1.validation.Isbn;

public record LivroDTO(
    @NotBlank(message = "O campo titulo é obrigatório")
    @Size(min = 3, max = 100, message = "O campo titulo deve ter entre 3 e 100 caracteres")
    String titulo,
    @NotBlank(message = "O campo autor é obrigatório")
    @Size(min = 3, max = 100, message = "O campo autor deve ter entre 3 e 100 caracteres")
    String autor,
    @NotBlank(message = "O ISBN é obrigatório")
    @Isbn
    String isbn,
    @NotNull(message = "O preço deve ser informado")
    @Positive(message = "O preço deve ser positivo")
    Double preco,
    @NotNull(message = "A data de publicação deve ser informada")
    LocalDate dataPublicacao,
    @NotNull(message = "A editora deve ser informada")
    @Positive(message = "Editora invalida")
    Long idEditora,
    @NotEmpty(message = "O livro deve pertencer a pelo menos uma categoria")
    List<Long> idsCategorias
){
}

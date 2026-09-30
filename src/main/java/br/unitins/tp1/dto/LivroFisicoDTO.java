package br.unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record LivroFisicoDTO(
    @NotBlank(message = "O campo titulo é obrigatório")
    @Size(min = 3, max = 100, message = "O campo titulo deve ter entre 3 e 100 caracteres")
    String titulo,
    @NotBlank(message = "O campo autor é obrigatório")
    @Size(min = 3, max = 100, message = "O campo autor deve ter entre 3 e 100 caracteres")
    String autor,
    @NotNull(message = "A editora deve ser informada")
    @Positive(message = "Editora invalida")
    Long idEditora,
    @NotNull(message = "O peso deve ser informado")
    @Positive(message = "O peso deve ser positivo")
    Double peso,
    @NotNull(message = "O estoque deve ser informado")
    @PositiveOrZero(message = "O estoque não pode ser negativo")
    Integer estoqueDisponivel
) {
}

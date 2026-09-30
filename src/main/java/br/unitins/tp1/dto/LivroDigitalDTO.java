package br.unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LivroDigitalDTO(
    @NotBlank(message = "O campo titulo é obrigatório")
    @Size(min = 3, max = 100, message = "O campo titulo deve ter entre 3 e 100 caracteres")
    String titulo,
    @NotBlank(message = "O campo autor é obrigatório")
    @Size(min = 3, max = 100, message = "O campo autor deve ter entre 3 e 100 caracteres")
    String autor,
    @NotNull(message = "A editora deve ser informada")
    @Positive(message = "Editora invalida")
    Long idEditora,
    @NotBlank(message = "O formato deve ser informado")
    String formato,
    @NotNull(message = "O tamanho do arquivo deve ser informado")
    @Positive(message = "O tamanho do arquivo deve ser positivo")
    Double tamanhoArquivo
) {
}

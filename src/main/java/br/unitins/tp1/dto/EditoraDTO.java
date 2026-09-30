package br.unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EditoraDTO(
    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
    String nome,   
    @NotNull(message = "O campo cnpj é obrigatório")
    String cnpj
    )     
{
}

package br.unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import br.unitins.tp1.validation.Cnpj;

public record EditoraDTO(
    @NotBlank(message = "O campo nome é obrigatório")
    @Size(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres")
    String nome,   
    @NotBlank(message = "O campo cnpj é obrigatório")
    @Cnpj
    String cnpj,
    @Size(max = 40, message = "O telefone deve ter no máximo 40 caracteres")
    String telefone,
    @Email(message = "O e-mail da editora é inválido")
    String email
    )     
{
}

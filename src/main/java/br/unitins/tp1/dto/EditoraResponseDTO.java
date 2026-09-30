package br.unitins.tp1.dto;
import br.unitins.tp1.model.Editora;

public record EditoraResponseDTO(
    Long id,
    String nome
) {
    public static EditoraResponseDTO fromEntity(Editora editora) {
        return new EditoraResponseDTO(
            editora.getId(),
            editora.getNome()
        );
    }
}
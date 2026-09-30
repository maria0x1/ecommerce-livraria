package br.unitins.tp1.dto;
import br.unitins.tp1.model.Editora;

public record EditoraDTOResponse(
    Long id,
    String nome
) {
    public static EditoraDTOResponse fromEntity(Editora editora) {
        return new EditoraDTOResponse(
            editora.getId(),
            editora.getNome()
        );
    }
}
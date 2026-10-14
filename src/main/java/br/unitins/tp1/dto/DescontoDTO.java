package br.unitins.tp1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.DecimalMax;

public record DescontoDTO(
        @NotNull(message = "O percentual de desconto é obrigatório")
        @PositiveOrZero(message = "O percentual de desconto não pode ser negativo")
        @DecimalMax(value = "100.0", message = "O desconto não pode exceder 100 por cento")
        Double percentual) {
}

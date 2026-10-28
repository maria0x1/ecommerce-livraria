package br.unitins.tp1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class LivroDomainTest {

    @Test
    void appliesOnlyValidDiscountPercentagesAndRoundsToCents() {
        LivroFisico livro = new LivroFisico();
        livro.setPreco(99.99);

        livro.aplicarDesconto(12.5);

        assertEquals(87.49, livro.getPreco());
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> livro.aplicarDesconto(101.0));
        assertEquals("O percentual de desconto deve estar entre 0 e 100.", exception.getMessage());
    }

    @Test
    void reducesStockOnlyWhenEnoughUnitsAreAvailable() {
        LivroFisico livro = new LivroFisico();
        livro.setQuantidadeEstoque(4);

        livro.baixarEstoque(3);

        assertEquals(1, livro.getQuantidadeEstoque());
        IllegalStateException stockException = assertThrows(
                IllegalStateException.class,
                () -> livro.baixarEstoque(2));
        assertEquals("Estoque insuficiente.", stockException.getMessage());

        IllegalArgumentException quantityException = assertThrows(
                IllegalArgumentException.class,
                () -> livro.baixarEstoque(0));
        assertEquals("A quantidade a baixar deve ser positiva.", quantityException.getMessage());
    }
}

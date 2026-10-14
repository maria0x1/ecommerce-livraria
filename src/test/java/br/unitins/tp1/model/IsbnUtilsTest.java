package br.unitins.tp1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IsbnUtilsTest {

    @Test
    void validatesAndNormalizesIsbn10AndIsbn13() {
        assertTrue(IsbnUtils.isValid("978-0-13-235088-4"));
        assertTrue(IsbnUtils.isValid("0-306-40615-2"));
        assertTrue(IsbnUtils.isValid("9788575224151"));
        assertFalse(IsbnUtils.isValid("9780132350885"));
        assertFalse(IsbnUtils.isValid("invalid"));
    }
}

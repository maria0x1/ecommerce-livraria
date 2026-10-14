package br.unitins.tp1.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CnpjUtilsTest {

    @Test
    void validatesFormattedAndUnformattedCnpj() {
        assertTrue(CnpjUtils.isValid("11.222.333/0001-81"));
        assertTrue(CnpjUtils.isValid("11222333000181"));
        assertTrue(CnpjUtils.isValid("11.444.777/0001-61"));
        assertTrue(CnpjUtils.isValid("AB.12C.D34/0001-84"));
        assertTrue(CnpjUtils.isValid("AB12CD34000184"));
        assertFalse(CnpjUtils.isValid("11.111.111/0001-00"));
        assertFalse(CnpjUtils.isValid("not-a-cnpj"));
        assertFalse(CnpjUtils.isValid("A11222333000181"));
        assertFalse(CnpjUtils.isValid("AB12CD34000185"));
        assertFalse(CnpjUtils.isValid(null));
    }
}

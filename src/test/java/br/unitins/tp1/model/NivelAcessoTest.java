package br.unitins.tp1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class NivelAcessoTest {

    @Test
    void resolvesStableIdsWithoutChangingEnumNames() {
        assertEquals(NivelAcesso.ROOT, NivelAcesso.valueOf(1));
        assertEquals(NivelAcesso.GERENTE, NivelAcesso.valueOf(2));
        assertEquals(NivelAcesso.OPERACIONAL, NivelAcesso.valueOf(3));
        assertNull(NivelAcesso.valueOf(99));
        assertEquals("ROOT", NivelAcesso.ROOT.name());
        assertEquals("Root", NivelAcesso.ROOT.getLABEL());
    }
}

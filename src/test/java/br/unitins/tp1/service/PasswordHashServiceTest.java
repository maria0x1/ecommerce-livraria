package br.unitins.tp1.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import br.unitins.tp1.model.UsuarioAdmin;
import br.unitins.tp1.model.PerfilAcesso;
import br.unitins.tp1.model.NivelAcesso;
import br.unitins.tp1.dto.UsuarioAdminResponseDTO;

class PasswordHashServiceTest {

    @Test
    void verifiesPasswordsAgainstSaltedHashes() {
        PasswordHashService service = new PasswordHashService();
        String hash = service.hash("LivrariaDev#2026");

        assertTrue(PasswordHashService.verify("LivrariaDev#2026", hash));
        assertFalse(PasswordHashService.verify("senha-incorreta", hash));
        assertFalse(PasswordHashService.verify("LivrariaDev#2026", "texto-invalido"));
        assertNotEquals(hash, service.hash("LivrariaDev#2026"));
    }

    @Test
    void authenticatesTheDevelopmentSeedHash() {
        UsuarioAdmin usuario = new UsuarioAdmin();
        usuario.setSenhaHash(
                "pbkdf2-sha256$210000$1Y1IkOC06Gdvukai0zj05A$4JJFGcxXkwLjGinPgNvmNHuzew4xBmGTaSm5G6EYrAU");

        assertTrue(usuario.autenticar("LivrariaDev#2026"));
        assertFalse(usuario.autenticar("wrong-password"));
    }

    @Test
    void serializedResponsesNeverContainPasswordOrHash() throws Exception {
        UsuarioAdmin usuario = new UsuarioAdmin();
        usuario.setId(1L);
        usuario.setNomeCompleto("Administrador");
        usuario.setLogin("root");
        usuario.setSenhaHash("private-hash");
        PerfilAcesso perfil = new PerfilAcesso();
        perfil.setNivel(NivelAcesso.ROOT);
        usuario.setPerfilAcesso(perfil);

        String response = new ObjectMapper().writeValueAsString(UsuarioAdminResponseDTO.valueOf(usuario));
        String entity = new ObjectMapper().writeValueAsString(usuario);

        assertFalse(response.contains("senha"));
        assertFalse(response.contains("private-hash"));
        assertFalse(entity.contains("senhaHash"));
        assertFalse(entity.contains("private-hash"));
    }
}

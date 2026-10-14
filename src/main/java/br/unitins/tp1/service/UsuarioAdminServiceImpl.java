package br.unitins.tp1.service;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.tp1.dto.UsuarioAdminDTO;
import br.unitins.tp1.dto.UsuarioAdminResponseDTO;
import br.unitins.tp1.model.PerfilAcesso;
import br.unitins.tp1.model.UsuarioAdmin;
import br.unitins.tp1.repository.UsuarioAdminRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioAdminServiceImpl implements UsuarioAdminService {

    @Inject
    UsuarioAdminRepository usuarioRepository;

    @Inject
    PasswordHashService passwordHashService;

    @Override
    @Transactional
    public UsuarioAdminResponseDTO create(UsuarioAdminDTO dto) {
        ensureLoginAvailable(dto.login(), null);
        UsuarioAdmin usuario = new UsuarioAdmin();
        usuario.setDataCriacao(LocalDateTime.now());
        aplicar(usuario, dto);
        usuarioRepository.persist(usuario);
        return UsuarioAdminResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioAdminResponseDTO update(Long id, UsuarioAdminDTO dto) {
        UsuarioAdmin usuario = findEntity(id);
        ensureLoginAvailable(dto.login(), id);
        aplicar(usuario, dto);
        return UsuarioAdminResponseDTO.valueOf(usuario);
    }

    @Override
    public List<UsuarioAdminResponseDTO> findAll() {
        return usuarioRepository.listAll().stream()
                .map(UsuarioAdminResponseDTO::valueOf)
                .toList();
    }

    @Override
    public UsuarioAdminResponseDTO findById(Long id) {
        return UsuarioAdminResponseDTO.valueOf(findEntity(id));
    }

    @Override
    public boolean autenticar(String login, String senhaAberta) {
        UsuarioAdmin usuario = usuarioRepository.find("login", login).firstResult();
        return usuario != null && usuario.autenticar(senhaAberta);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!usuarioRepository.deleteById(id)) {
            throw new NotFoundException();
        }
    }

    private void aplicar(UsuarioAdmin usuario, UsuarioAdminDTO dto) {
        usuario.setNomeCompleto(dto.nomeCompleto());
        usuario.setLogin(dto.login());
        usuario.setSenhaHash(passwordHashService.hash(dto.senha()));

        PerfilAcesso perfil = new PerfilAcesso();
        perfil.setNivel(dto.perfilAcesso().nivel());
        perfil.setPermissoesDetalhadas(dto.perfilAcesso().permissoesDetalhadas());
        usuario.setPerfilAcesso(perfil);
    }

    private UsuarioAdmin findEntity(Long id) {
        UsuarioAdmin usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new NotFoundException();
        }
        return usuario;
    }

    private void ensureLoginAvailable(String login, Long currentId) {
        UsuarioAdmin existente = usuarioRepository.find("login", login).firstResult();
        if (existente != null && !existente.getId().equals(currentId)) {
            throw new BadRequestException("O login informado já está em uso.");
        }
    }
}

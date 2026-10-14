package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.EditoraDTO;
import br.unitins.tp1.dto.EditoraResponseDTO;
import br.unitins.tp1.model.CnpjUtils;
import br.unitins.tp1.model.Editora;
import br.unitins.tp1.repository.EditoraRepository;
import br.unitins.tp1.repository.LivroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EditoraServiceImpl implements EditoraService {

    @Inject
    EditoraRepository editoraRepository;

    @Inject
    LivroRepository livroRepository;

    @Override
    @Transactional
    public EditoraResponseDTO create(EditoraDTO dto) {
        ensureCnpjAvailable(dto.cnpj(), null);
        Editora editora = new Editora();
        aplicar(editora, dto);
        editoraRepository.persist(editora);
        return EditoraResponseDTO.fromEntity(editora);
    }

    @Override
    @Transactional
    public EditoraResponseDTO update(Long id, EditoraDTO dto) {
        Editora editora = findEntity(id);
        ensureCnpjAvailable(dto.cnpj(), id);
        aplicar(editora, dto);
        return EditoraResponseDTO.fromEntity(editora);
    }

    @Override
    public List<EditoraResponseDTO> findAll() {
        return editoraRepository.listAll().stream()
                .map(EditoraResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public EditoraResponseDTO findById(Long id) {
        return EditoraResponseDTO.fromEntity(findEntity(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (editoraRepository.findById(id) == null) {
            throw new NotFoundException();
        }
        if (livroRepository.count("editora.id", id) > 0) {
            throw new BadRequestException("A editora está associada a um ou mais livros.");
        }
        if (!editoraRepository.deleteById(id)) {
            throw new NotFoundException();
        }
    }

    private void aplicar(Editora editora, EditoraDTO dto) {
        editora.setNome(dto.nome());
        editora.setCnpj(CnpjUtils.normalize(dto.cnpj()));
        editora.setTelefone(dto.telefone());
        editora.setEmail(dto.email());
    }

    private void ensureCnpjAvailable(String cnpj, Long currentId) {
        Editora existente = editoraRepository.find("cnpj", CnpjUtils.normalize(cnpj)).firstResult();
        if (existente != null && !existente.getId().equals(currentId)) {
            throw new BadRequestException("O CNPJ informado já está cadastrado.");
        }
    }

    private Editora findEntity(Long id) {
        Editora editora = editoraRepository.findById(id);
        if (editora == null) {
            throw new NotFoundException();
        }
        return editora;
    }
}

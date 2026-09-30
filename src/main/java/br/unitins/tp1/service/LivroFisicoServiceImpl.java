package br.unitins.tp1.service;

import br.unitins.tp1.dto.LivroFisicoDTO;
import br.unitins.tp1.dto.LivroFisicoResponseDTO;
import br.unitins.tp1.model.Editora;
import br.unitins.tp1.model.LivroFisico;
import br.unitins.tp1.repository.EditoraRepository;
import br.unitins.tp1.repository.LivroFisicoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class LivroFisicoServiceImpl implements LivroFisicoService {

    @Inject
    LivroFisicoRepository livroFisicoRepository;

    @Inject
    EditoraRepository editoraRepository;

    @Override
    @Transactional
    public LivroFisicoResponseDTO create(@Valid LivroFisicoDTO dto) {
        LivroFisico livro = new LivroFisico();
        preencher(livro, dto);
        livroFisicoRepository.persist(livro);
        return LivroFisicoResponseDTO.fromEntity(livro);
    }

    @Override
    @Transactional
    public LivroFisicoResponseDTO update(Long id, @Valid LivroFisicoDTO dto) {
        LivroFisico livro = livroFisicoRepository.findById(id);
        if (livro == null) {
            return null;
        }

        preencher(livro, dto);
        return LivroFisicoResponseDTO.fromEntity(livro);
    }

    @Override
    public LivroFisicoResponseDTO findById(Long id) {
        LivroFisico livro = livroFisicoRepository.findById(id);
        return livro == null ? null : LivroFisicoResponseDTO.fromEntity(livro);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        livroFisicoRepository.deleteById(id);
    }

    private void preencher(LivroFisico livro, LivroFisicoDTO dto) {
        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());
        livro.setPeso(dto.peso());
        livro.setEstoqueDisponivel(dto.estoqueDisponivel());

        Editora editora = editoraRepository.findById(dto.idEditora());
        livro.setEditora(editora);
    }
}

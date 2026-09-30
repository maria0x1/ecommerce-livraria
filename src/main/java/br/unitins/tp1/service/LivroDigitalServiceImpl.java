package br.unitins.tp1.service;

import br.unitins.tp1.dto.LivroDigitalDTO;
import br.unitins.tp1.dto.LivroDigitalResponseDTO;
import br.unitins.tp1.model.Editora;
import br.unitins.tp1.model.LivroDigital;
import br.unitins.tp1.repository.EditoraRepository;
import br.unitins.tp1.repository.LivroDigitalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class LivroDigitalServiceImpl implements LivroDigitalService {

    @Inject
    LivroDigitalRepository livroDigitalRepository;

    @Inject
    EditoraRepository editoraRepository;

    @Override
    @Transactional
    public LivroDigitalResponseDTO create(@Valid LivroDigitalDTO dto) {
        LivroDigital livro = new LivroDigital();
        preencher(livro, dto);
        livroDigitalRepository.persist(livro);
        return LivroDigitalResponseDTO.fromEntity(livro);
    }

    @Override
    @Transactional
    public LivroDigitalResponseDTO update(Long id, @Valid LivroDigitalDTO dto) {
        LivroDigital livro = livroDigitalRepository.findById(id);
        if (livro == null) {
            return null;
        }

        preencher(livro, dto);
        return LivroDigitalResponseDTO.fromEntity(livro);
    }

    @Override
    public LivroDigitalResponseDTO findById(Long id) {
        LivroDigital livro = livroDigitalRepository.findById(id);
        return livro == null ? null : LivroDigitalResponseDTO.fromEntity(livro);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        livroDigitalRepository.deleteById(id);
    }

    private void preencher(LivroDigital livro, LivroDigitalDTO dto) {
        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());
        livro.setFormato(dto.formato());
        livro.setTamanhoArquivo(dto.tamanhoArquivo());

        Editora editora = editoraRepository.findById(dto.idEditora());
        livro.setEditora(editora);
    }
}

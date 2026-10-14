package br.unitins.tp1.service;

import br.unitins.tp1.dto.LivroDigitalDTO;
import br.unitins.tp1.dto.LivroDigitalResponseDTO;
import br.unitins.tp1.model.LivroDigital;
import br.unitins.tp1.repository.LivroDigitalRepository;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class LivroDigitalServiceImpl implements LivroDigitalService {

    @Inject
    LivroDigitalRepository livroDigitalRepository;

    @Inject
    LivroSupportService livroSupportService;

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
            throw new NotFoundException();
        }

        preencher(livro, dto);
        return LivroDigitalResponseDTO.fromEntity(livro);
    }

    @Override
    @Transactional
    public LivroDigitalResponseDTO findById(Long id) {
        LivroDigital livro = livroDigitalRepository.findById(id);
        if (livro == null) {
            throw new NotFoundException();
        }
        return LivroDigitalResponseDTO.fromEntity(livro);
    }

    @Override
    @Transactional
    public List<LivroDigitalResponseDTO> findAll() {
        return livroDigitalRepository.listAll().stream()
                .map(LivroDigitalResponseDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!livroDigitalRepository.deleteById(id)) {
            throw new NotFoundException();
        }
    }

    @Override
    @Transactional
    public LivroDigitalResponseDTO aplicarDesconto(Long id, Double percentual) {
        LivroDigital livro = livroDigitalRepository.findById(id);
        if (livro == null) {
            throw new NotFoundException();
        }
        try {
            livro.aplicarDesconto(percentual);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            throw new jakarta.ws.rs.BadRequestException(exception.getMessage(), exception);
        }
        return LivroDigitalResponseDTO.fromEntity(livro);
    }

    private void preencher(LivroDigital livro, LivroDigitalDTO dto) {
        livroSupportService.preencherDadosComuns(
                livro, dto.titulo(), dto.autor(), dto.isbn(), dto.preco(), dto.dataPublicacao(),
                dto.idEditora(), dto.idsCategorias());
        livro.setFormatoArquivo(dto.formatoArquivo());
        livro.setTamanhoMb(dto.tamanhoMb());
        livro.setLicencaDrm(dto.licencaDrm());
    }
}

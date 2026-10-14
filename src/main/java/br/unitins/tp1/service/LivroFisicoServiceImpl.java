package br.unitins.tp1.service;

import br.unitins.tp1.dto.LivroFisicoDTO;
import br.unitins.tp1.dto.LivroFisicoResponseDTO;
import br.unitins.tp1.model.LivroFisico;
import br.unitins.tp1.repository.LivroFisicoRepository;
import java.util.List;
import jakarta.persistence.LockModeType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class LivroFisicoServiceImpl implements LivroFisicoService {

    @Inject
    LivroFisicoRepository livroFisicoRepository;

    @Inject
    LivroSupportService livroSupportService;

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
            throw new NotFoundException();
        }

        preencher(livro, dto);
        return LivroFisicoResponseDTO.fromEntity(livro);
    }

    @Override
    @Transactional
    public LivroFisicoResponseDTO findById(Long id) {
        LivroFisico livro = livroFisicoRepository.findById(id);
        if (livro == null) {
            throw new NotFoundException();
        }
        return LivroFisicoResponseDTO.fromEntity(livro);
    }

    @Override
    @Transactional
    public List<LivroFisicoResponseDTO> findAll() {
        return livroFisicoRepository.listAll().stream()
                .map(LivroFisicoResponseDTO::fromEntity)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!livroFisicoRepository.deleteById(id)) {
            throw new NotFoundException();
        }
    }

    @Override
    @Transactional
    public LivroFisicoResponseDTO baixarEstoque(Long id, Integer quantidade) {
        LivroFisico livro = livroFisicoRepository.findById(id, LockModeType.PESSIMISTIC_WRITE);
        if (livro == null) {
            throw new NotFoundException();
        }
        try {
            livro.baixarEstoque(quantidade);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            throw new jakarta.ws.rs.BadRequestException(exception.getMessage(), exception);
        }
        return LivroFisicoResponseDTO.fromEntity(livro);
    }

    @Override
    @Transactional
    public LivroFisicoResponseDTO aplicarDesconto(Long id, Double percentual) {
        LivroFisico livro = livroFisicoRepository.findById(id);
        if (livro == null) {
            throw new NotFoundException();
        }
        try {
            livro.aplicarDesconto(percentual);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            throw new jakarta.ws.rs.BadRequestException(exception.getMessage(), exception);
        }
        return LivroFisicoResponseDTO.fromEntity(livro);
    }

    private void preencher(LivroFisico livro, LivroFisicoDTO dto) {
        livroSupportService.preencherDadosComuns(
                livro, dto.titulo(), dto.autor(), dto.isbn(), dto.preco(), dto.dataPublicacao(),
                dto.idEditora(), dto.idsCategorias());
        livro.setPesoKg(dto.pesoKg());
        livro.setQuantidadeEstoque(dto.quantidadeEstoque());
        livro.setTipoCapa(dto.tipoCapa());
    }
}

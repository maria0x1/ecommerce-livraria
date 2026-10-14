package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.CategoriaDTO;
import br.unitins.tp1.dto.CategoriaResponseDTO;
import br.unitins.tp1.model.Categoria;
import br.unitins.tp1.repository.CategoriaRepository;
import br.unitins.tp1.repository.LivroRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.BadRequestException;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {

    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    LivroRepository livroRepository;

    @Override
    @Transactional
    public CategoriaResponseDTO create(CategoriaDTO dto) {
        Categoria categoria = dto.toEntity();
        categoriaRepository.persist(categoria);
        return CategoriaResponseDTO.valueOf(categoria);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO update(Long id, CategoriaDTO dto) {
        Categoria categoria = findEntity(id);
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());
        categoria.setAtivo(dto.ativo() == null ? Boolean.TRUE : dto.ativo());
        return CategoriaResponseDTO.valueOf(categoria);
    }

    @Override
    public List<CategoriaResponseDTO> findAll() {
        return categoriaRepository.listAll().stream()
                .map(CategoriaResponseDTO::valueOf)
                .toList();
    }

    @Override
    public CategoriaResponseDTO findById(Long id) {
        return CategoriaResponseDTO.valueOf(findEntity(id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (categoriaRepository.findById(id) == null) {
            throw new NotFoundException();
        }
        if (livroRepository.count("categorias.id", id) > 0) {
            throw new BadRequestException("A categoria está associada a um ou mais livros.");
        }
        if (!categoriaRepository.deleteById(id)) {
            throw new NotFoundException();
        }
    }

    private Categoria findEntity(Long id) {
        Categoria categoria = categoriaRepository.findById(id);
        if (categoria == null) {
            throw new NotFoundException();
        }
        return categoria;
    }
}

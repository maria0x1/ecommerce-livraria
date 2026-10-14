package br.unitins.tp1.service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import br.unitins.tp1.model.Categoria;
import br.unitins.tp1.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class LivroCategoriaService {

    @Inject
    CategoriaRepository categoriaRepository;

    public List<Categoria> findCategorias(List<Long> idsCategorias) {
        if (idsCategorias == null || idsCategorias.isEmpty()) {
            throw new BadRequestException("O livro deve pertencer a pelo menos uma categoria.");
        }

        List<Categoria> categorias = new ArrayList<>();
        for (Long id : new LinkedHashSet<>(idsCategorias)) {
            if (id == null || id <= 0) {
                throw new BadRequestException("Os IDs das categorias devem ser positivos.");
            }
            Categoria categoria = categoriaRepository.findById(id);
            if (categoria == null) {
                throw new NotFoundException();
            }
            categorias.add(categoria);
        }
        return categorias;
    }
}

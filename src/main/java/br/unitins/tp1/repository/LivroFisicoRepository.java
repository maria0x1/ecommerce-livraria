package br.unitins.tp1.repository;

import br.unitins.tp1.model.LivroFisico;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LivroFisicoRepository implements PanacheRepository<LivroFisico> {
}

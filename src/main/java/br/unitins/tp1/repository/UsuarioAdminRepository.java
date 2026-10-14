package br.unitins.tp1.repository;

import br.unitins.tp1.model.UsuarioAdmin;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioAdminRepository implements PanacheRepository<UsuarioAdmin> {
}

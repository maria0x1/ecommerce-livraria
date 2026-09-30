package br.unitins.tp1.repository;

import br.unitins.tp1.model.Editora;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped 
public class EditoraRepository implements PanacheRepository<Editora> {
        public List<Editora> findByNome(String nome) {
        return find("upper(nome) LIKE upper(?1)", "%" + nome + "%").list();
    }
}

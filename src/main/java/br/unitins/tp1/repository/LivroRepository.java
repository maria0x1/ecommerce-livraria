package br.unitins.tp1.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import br.unitins.tp1.model.Livro;
import java.util.List;  

@ApplicationScoped

public class LivroRepository implements PanacheRepository<Livro>{
    public List<Livro> findByTitulo(String titulo){
        return find("upper(titulo) like ?1", "%" + titulo.toUpperCase() + "%").list();
    }

    public List<Livro> findByAutor(String autor){
        return find("upper(autor) like ?1", "%" + autor.toUpperCase() + "%").list();
    }

    public List<Livro> findByEditora(String editora){
        return find("upper(editora) like ?1", "%" + editora.toUpperCase() + "%").list();    
    }
}

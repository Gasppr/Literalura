package br.com.gaspp.literalura.repository;

import br.com.gaspp.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {


    @Query("SELECT a FROM Author a WHERE a.death_year >= :year")
    List<Author> findByAuthorsAlive(int year);

    
}

package br.com.gaspp.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.gaspp.literalura.model.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitleContainingIgnoreCase(String titleBook);

}

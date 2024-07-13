package br.com.literalura.repository;

import br.com.literalura.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findAllByLanguagesContains(String language);
}

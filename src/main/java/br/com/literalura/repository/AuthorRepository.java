package br.com.literalura.repository;

import br.com.literalura.model.entity.Author;
import br.com.literalura.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


}

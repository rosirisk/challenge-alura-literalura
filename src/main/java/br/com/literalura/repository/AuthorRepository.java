package br.com.literalura.repository;

import br.com.literalura.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    Optional<Author> findAllByNameContains(String name);

    List<Author> findAllByBirthYear(Long year);

    List<Author> findAllByDeathYear(Long year);

    List<Author> findAllByBirthYearIsBeforeAndDeathYearIsAfter(Long year, Long year2);
}

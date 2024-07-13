package br.com.literalura.service;

import br.com.literalura.model.entity.Author;
import br.com.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

    public Optional<Author> findByName(String name){
        return repository.findAllByNameContains(name);
    }

    public List<Author> findByDeathYear(Long year){
        return repository.findAllByDeathYear(year);
    }
    public List<Author> listAll() {
        return repository.findAll();
    }

    public List<Author> findByBirthYear(Long ano) {
        return repository.findAllByBirthYear(ano);
    }

    public List<Author> findLive(Long ano) {
        return repository.findAllByBirthYearIsBeforeAndDeathYearIsAfter(ano,ano);
    }
}

package br.com.literalura.service;

import br.com.literalura.model.entity.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    public Author findByName(String name){
        return null;
    }

    public Author findByBirthYear(String year){
        return null;
    }
    public Author findByDeathYear(Long year){
        return null;
    }
    public Author liveAuthor(Long year){
//        author que estava vivo no ano que o usuario informou
        return null;
    }

}

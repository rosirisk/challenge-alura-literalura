package br.com.literalura.service;

import br.com.literalura.Api;
import br.com.literalura.model.BookRecord;
import br.com.literalura.model.entity.Book;
import br.com.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private Api api = new Api();

    @Autowired
    private BookRepository repository;

    public Optional<Book> findByTitle(String author, String title) throws IOException, InterruptedException {
        List<BookRecord> bookRecords = api.requestData(author, title);
        if (bookRecords.isEmpty()) {
            return Optional.empty();
        }
        List<Book> books = bookRecords.stream()
                .map(b -> new Book(b))
                .collect(Collectors.toList());
        try{
            repository.save(books.get(0));
        }catch (DataIntegrityViolationException e){
            System.out.println("Registro ja existente na base.. nao sera inserido");
        }
        return Optional.of(books.get(0));
    }

    public List<Book> listAllBooks() {
        return repository.findAll();
    }

    public List<Book> listAllBooksByLanguage(String language) {
        return repository.findAllByLanguagesContains(language);
    }

    public List<Book> listAllBooksByAuthor(String author) {
        return repository.findAllByAuthorsNameContains(author);
    }
}

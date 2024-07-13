package br.com.literalura.service;

import br.com.literalura.Api;
import br.com.literalura.model.BookRecord;
import br.com.literalura.model.entity.Book;
import br.com.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private Api api = new Api();

    @Autowired
    private BookRepository repository;

    public List<BookRecord> findByTitle(String author, String title) throws IOException, InterruptedException {
        List<BookRecord> bookRecords = api.requestData(author, title);
        List<Book> books = bookRecords.stream()
                .map(b -> new Book(b))
                .collect(Collectors.toList());
        repository.saveAll(books);
        return bookRecords;
    }

    public long countByLanguage(String language){
        //todo filtrar por idioma
        return 0;
    }


    public List<Book> listAllBooks() {
        return repository.findAll();
    }

    public List<Book> listAllBooksByLanguage(String language) {
        return repository.findAllByLanguagesContains(language);
    }
}

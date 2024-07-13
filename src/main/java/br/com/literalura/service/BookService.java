package br.com.literalura.service;

import br.com.literalura.Api;
import br.com.literalura.model.Book;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BookService {

    private Api api = new Api();


    public List<Book> findByTitle(String author, String title) throws IOException, InterruptedException {
        List<Book> books = api.requestData(author, title);
        //todo converter e gravar livros no BD
        return books;
    }

}

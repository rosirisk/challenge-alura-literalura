package br.com.literalura;

import br.com.literalura.model.BookRecord;
import br.com.literalura.model.entity.Book;
import br.com.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    private final Scanner leitura = new Scanner(System.in);
    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            showMenu();
            var opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> getBooyByAuthorAndTitle();
                case 0 -> {
                    break;
                }
                default -> System.out.println("Opção inválida!");
            }
        }


    }

    private void showMenu() {
        System.out.println("""
           Escolha uma opção abaixo:
               1 - Buscar livros pelo título
               2 - Listar todos os livros cadastrados
               3 - Busca de livros com base no idioma
               4 - Listar todos os autores
               5 - Buscar livros pelo nome do Autor
               6 - Buscar livros pelo ano de nascimento do Autor
               7 - Buscar livros pelo ano de falecimento do Autor
               8 - Listar todos os autores vivos no ano pesquisado
               0- Sair
            """);
    }
    private void getBooyByAuthorAndTitle() throws IOException, InterruptedException {
        List<BookRecord> teste = bookService.findByTitle("dickens", "great");
        //TODO transformar no DTO Livro conforme a spec.
        System.out.println(teste);
    }
    private void listAllBooks() {
        List<Book> books = bookService.listAllBooks();
        //TODO transformar no DTO Livro conforme a spec.
        System.out.println(books);
    }
    private void listAllBooksByLanguage(String language) {
        List<Book> books = bookService.listAllBooksByLanguage(language);
        //TODO transformar no DTO Livro conforme a spec.
        System.out.println(books);
    }

    private void listAllAuthors() {
        List<Book> books = bookService.listAllBooks();
        //TODO transformar no DTO Livro conforme a spec.
        System.out.println(books);
    }
}

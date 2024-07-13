package br.com.literalura;

import br.com.literalura.model.entity.Author;
import br.com.literalura.model.entity.Book;
import br.com.literalura.service.AuthorService;
import br.com.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

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
                case 2 -> listAllBooks();
                case 3 -> listAllBooksByLanguage();
                case 4 -> ListAllBooksByAuthor();
                case 5 -> listAllAuthors();
                case 6 -> findAuthorByName();
                case 7 -> findAuthorByNascimento();
                case 8 -> findAuthorByMorte();
                case 9 -> findAuthorLive();
                case 0 -> { System.exit(0);}
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
               4 - Buscar livros pelo nome do Autor
               5 - Listar todos os autores
               6 - Buscar autor pelo nome
               7 - Buscar autores pelo ano de nascimento
               8 - Buscar autores pelo ano de falecimento 
               9 - Listar todos os autores vivos no ano pesquisado
               0- Sair
            """);
    }
    Scanner scanner = new Scanner(System.in);
    private void getBooyByAuthorAndTitle() throws IOException, InterruptedException {
        System.out.println("Informe o autor");
        String author = scanner.nextLine();
        System.out.println("Informe o titulo do livro");
        String title = scanner.nextLine();
        Optional<Book> book = bookService.findByTitle(author, title);
        if (book.isPresent()) {
            System.out.println(book.get()+"\n");
        }else{
            System.out.println("Nenhum resultado encontrado");
        }
    }
    private void listAllBooks() {
        List<Book> books = bookService.listAllBooks();
        System.out.println(books+"\n");
    }
    private void listAllBooksByLanguage() {
        System.out.println("Informe o idioma");
        String allBooksLanguage = scanner.nextLine();
        List<Book> books = bookService.listAllBooksByLanguage(allBooksLanguage);
        System.out.println(books+"\n");
    }

    private void listAllAuthors() {
        List<Author> authors = authorService.listAll();
        System.out.println(authors+"\n");
    }
    private void ListAllBooksByAuthor() {
        System.out.println("Infome o nome do autor");
        String nameAuthor = scanner.nextLine();
        List<Book> books = bookService.listAllBooksByAuthor(nameAuthor);
        System.out.println(books+"\n");
    }
    private void findAuthorByName() {
        System.out.println("Infome o nome do autor");
        String nameAuthor = scanner.nextLine();
        Optional<Author> author = authorService.findByName(nameAuthor);
        System.out.println(author+"\n");
    }
    private void findAuthorByMorte() {
        System.out.println("Informe o ano do falecimento do autor");
        long authorDeath = scanner.nextLong();
        List<Author> authors = authorService.findByDeathYear(authorDeath);
        System.out.println(authors+"\n");
    }
    private void findAuthorByNascimento() {
        System.out.println("Informe o ano do nascimento do autor");
        long authorBirth = scanner.nextLong();
        List<Author> authors = authorService.findByBirthYear(authorBirth);
        System.out.println(authors+"\n");
    }
    private void findAuthorLive() {
        System.out.println("Informe o ano para pesquisa");
        long authorLive = scanner.nextLong();
        List<Author> authors = authorService.findLive(authorLive);
        System.out.println(authors+"\n");
    }
}

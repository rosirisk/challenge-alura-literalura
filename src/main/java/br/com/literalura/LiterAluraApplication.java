package br.com.literalura;

import br.com.literalura.model.Book;
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
                       1- Buscar livros pelo título
                       0- Sair
            """);
    }
    private void getBooyByAuthorAndTitle() throws IOException, InterruptedException {
        List<Book> teste = bookService.findByTitle("dickens", "great");
        System.out.println(teste);
    }
}

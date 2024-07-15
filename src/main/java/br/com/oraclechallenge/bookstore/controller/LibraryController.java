package br.com.oraclechallenge.bookstore.controller;

import br.com.oraclechallenge.bookstore.model.Author;
import br.com.oraclechallenge.bookstore.model.Book;
import br.com.oraclechallenge.bookstore.service.GutendexService;
import br.com.oraclechallenge.bookstore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Controller
public class LibraryController {

    @Autowired
    private GutendexService gutendexService;

    @Autowired
    private LibraryService libraryService;

    public void menu(String... args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 - Contribute to our library");
            System.out.println("2 - List all registered books.");
            System.out.println("3 - List all registered authors.");
            System.out.println("4 - List all authors alive on a given date.");
            System.out.println("5 - List all books in a given language.");
            System.out.println("6 - Leave.");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            switch (option) {
                case 1:
                    contributeToLibrary(scanner);
                    break;
                case 2:
                    listAllBooks();
                    break;
                case 3:
                    listAllAuthors();
                    break;
                case 4:
                    listAuthorsAliveOnDate(scanner);
                    break;
                case 5:
                    listBooksByLanguage(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void contributeToLibrary(Scanner scanner) {
        System.out.print("Enter the name or snippet of the book: ");
        String query = scanner.nextLine();

        List<Book> books = gutendexService.searchBooks(query);
        if (books.isEmpty()) {
            System.out.println("No books found for the given query.");
            return;
        }

        books.forEach(book -> {
            libraryService.saveBookAndAuthors(book);
            System.out.println("Book and authors saved successfully!");
        });
    }

    private void listAllBooks() {
        List<Book> books = libraryService.listAllBooks();
        books.forEach(book -> System.out.println("Title: " + book.getTitle() + ", Language: " + book.getLanguage()));
    }

    private void listAllAuthors() {
        List<Author> authors = libraryService.listAllAuthors();
        authors.forEach(author -> System.out.println("Name: " + author.getName() + ", Birth Date: " + author.getBirthDate() + ", Death Date: " + (author.getDeathDate() != null ? author.getDeathDate() : "N/A")));
    }

    private void listAuthorsAliveOnDate(Scanner scanner) {
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        List<Author> authors = libraryService.listAuthorsAliveOnDate(date);
        authors.forEach(author -> System.out.println("Name: " + author.getName() + ", Birth Date: " + author.getBirthDate() + ", Death Date: " + (author.getDeathDate() != null ? author.getDeathDate() : "N/A")));
    }

    private void listBooksByLanguage(Scanner scanner) {
        System.out.print("Enter the language: ");
        String language = scanner.nextLine();

        List<Book> books = libraryService.listBooksByLanguage(language);
        books.forEach(book -> System.out.println("Title: " + book.getTitle() + ", Language: " + book.getLanguage()));
    }
}


package br.com.oraclechallenge.bookstore.controller;

import br.com.oraclechallenge.bookstore.model.Book;
import br.com.oraclechallenge.bookstore.model.Person;
import br.com.oraclechallenge.bookstore.service.GutendexService;
import br.com.oraclechallenge.bookstore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Scanner;

@Controller
public class LibraryController {

    private final GutendexService gutendexService;
    private final LibraryService libraryService;
    private final Scanner scanner;

    @Autowired
    public LibraryController(GutendexService gutendexService, LibraryService libraryService) {
        this.gutendexService = gutendexService;
        this.libraryService = libraryService;
        this.scanner = new Scanner(System.in);
    }

    public void menu(String... args) {
        while (true) {
            System.out.println("1 - Contribute to our library");
            System.out.println("2 - List all registered books.");
            System.out.println("3 - List all registered authors.");
            System.out.println("4 - List all authors alive on a given date.");
            System.out.println("5 - List all books in a given language.");
            System.out.println("6 - Leave.");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    contributeToLibrary();
                    break;
                case 2:
                    listAllBooks();
                    break;
                case 3:
                    listAllAuthors();
                    break;
                case 4:
                    listAuthorsAliveOnDate();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void contributeToLibrary() {
        System.out.print("Enter the name or snippet of the book: ");
        String query = scanner.nextLine();
        gutendexService.searchBooks(query);
    }

    private void listAllBooks() {
        List<Book> books = libraryService.listAllBooks();
        books.forEach(book -> System.out.println("Title: " + book.getTitle() + ", Language: " + book.getLanguage()));
    }

    private void listAllAuthors() {
        List<Person> authors = libraryService.listAllAuthors();
        authors.forEach(author -> System.out.println("Name: " + author.getName() + ", Birth Date: " + author.getBirthDate() + ", Death Date: " + (author.getDeathDate() != null ? author.getDeathDate() : "N/A")));
    }

    private void listAuthorsAliveOnDate() {
        System.out.print("Enter a tear (YYYY): ");
        String dateStr = scanner.nextLine();
        Year date = Year.parse(dateStr);

        List<Person> authors = libraryService.listAuthorsAliveOnDate(date);
        authors.forEach(author -> System.out.println("Name: " + author.getName() + ", Birth Date: " + author.getBirthDate() + ", Death Date: " + (author.getDeathDate() != null ? author.getDeathDate() : "N/A")));
    }

    private void listBooksByLanguage() {
        System.out.print("Enter the language: ");
        String language = scanner.nextLine();

        List<Book> books = libraryService.findBooksByLanguage(language);
        books.forEach(book -> System.out.println("Title: " + book.getTitle() + ", Language: " + book.getLanguage()));
    }
}

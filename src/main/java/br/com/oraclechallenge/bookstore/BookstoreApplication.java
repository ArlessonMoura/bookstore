package br.com.oraclechallenge.bookstore;

import br.com.oraclechallenge.bookstore.controller.LibraryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookstoreApplication implements CommandLineRunner {

	private final LibraryController libraryController;

	@Autowired
	public BookstoreApplication(LibraryController libraryController) {
		this.libraryController = libraryController;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		libraryController.menu(args);
	}
}

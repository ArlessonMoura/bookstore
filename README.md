
<p>
  <img src="https://raw.githubusercontent.com/ArlessonMoura/currency-converter/main/src/assets/Template%2BONE%2BAluno%2BBack%2BEND_PT%2Bv3.png" alt="ONE BANNER">
</p>

# Library System - Spring Boot Application

This project is a Java Spring Boot application to manage a library through a command-line interface (CLI).

## Features

The application provides the following features:

1. **Contribute to our library**: Allows users to fetch book information from the Gutendex API and save it to a PostgreSQL database.
2. **List all registered books**: Lists all books stored in the database.
3. **List all registered authors**: Lists all authors stored in the database.
4. **List authors alive on a given date**: Lists authors who were alive on a specific date based on birth and death information.
5. **List books in a given language**: Lists all books stored in the database in a specific language.

## MVC Architecture and Project Structure

The project follows an MVC (Model-View-Controller) architecture for separation of concerns.

1**Running the Application**:
    - Use Maven to compile and run the application:

      ```
      mvn spring-boot:run
      ```

2**Interacting with the Application**:
    - Once the application is started, you can interact with the system via the command-line interface using the available options.

## Dependencies

- Spring Boot 3.3.1
- Spring Data JPA
- Hibernate ORM
- PostgreSQL JDBC Driver

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).



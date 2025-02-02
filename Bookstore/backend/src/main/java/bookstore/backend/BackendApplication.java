package bookstore.backend;

import bookstore.backend.domain.Book;
import bookstore.backend.domain.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@Bean
	CommandLineRunner runner(BookRepository bookRepository) {
		return args -> {
			bookRepository.save(new Book("A Farewell to Arms", "Ernest Hemingway", "1234", "1929", "20"));
		};
	}
}

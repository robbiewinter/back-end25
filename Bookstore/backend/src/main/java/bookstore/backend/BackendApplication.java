package bookstore.backend;

import bookstore.backend.domain.Category;
import bookstore.backend.domain.CategoryRepository;
import bookstore.backend.domain.User;
import bookstore.backend.domain.UserRepository;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import bookstore.backend.domain.Book;
import bookstore.backend.domain.BookRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BackendApplication.class);
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        return args -> {
            Category fiction = new Category("Fiction");
            categoryRepository.save(fiction);

            Category drama = new Category("Drama");
            categoryRepository.save(drama);

            Book book1 = new Book("A Farewell to Arms", "Ernest Hemingway", "1234", 1929, 20.0);
            book1.setCategory(fiction);
            bookRepository.save(book1);

            Book book2 = new Book("Animal Farm", "George Orwell", "1235", 1945, 15.0);
            book2.setCategory(drama);
            bookRepository.save(book2);

            bookRepository.findAll().forEach(System.out::println);

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            User user1 = new User("user", encoder.encode("password"), "USER", "user1@email");
            User user2 = new User("admin", encoder.encode("password"), "ADMIN", "user2@email");

            userRepository.saveAll(Arrays.asList(user1, user2));
        };
    }
}


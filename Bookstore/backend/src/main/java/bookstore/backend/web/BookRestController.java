package bookstore.backend.web;

import bookstore.backend.domain.Book;
import bookstore.backend.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookRestController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Optional<Book> getBookById(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }
}

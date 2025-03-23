package bookstore.backend.web;

import bookstore.backend.domain.Book;
import bookstore.backend.domain.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookRestController {

    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } 
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}

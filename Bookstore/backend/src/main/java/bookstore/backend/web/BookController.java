package bookstore.backend.web;

import bookstore.backend.domain.Book;
import bookstore.backend.domain.BookRepository;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/index")
    public List<Book> books() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Book addBook(@RequestParam Book book) {
        bookRepository.save(book);
        return bookRepository.save(book);
    }
}



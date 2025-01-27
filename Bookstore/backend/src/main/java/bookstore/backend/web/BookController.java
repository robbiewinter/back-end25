package bookstore.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BookController {

    @GetMapping("/index")
    public String getBook(@RequestParam String book) {
        return book;
    }
    
}

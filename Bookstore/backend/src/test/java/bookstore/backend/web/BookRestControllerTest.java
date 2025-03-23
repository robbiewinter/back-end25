package bookstore.backend.web;

import bookstore.backend.domain.Book;
import bookstore.backend.domain.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookRestController bookRestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book One");
        book1.setAuthor("Author One");
        book1.setIsbn("1234567890");
        book1.setPublicationYear(2021);
        book1.setPrice(10.0);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book Two");
        book2.setAuthor("Author Two");
        book2.setIsbn("456456456");
        book2.setPublicationYear(2022);
        book2.setPrice(15.0);

        given(bookRepository.findAll()).willReturn(Arrays.asList(book1, book2));
        given(bookRepository.findById(1L)).willReturn(Optional.of(book1));
        given(bookRepository.findById(2L)).willReturn(Optional.of(book2));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetBookById() throws Exception {
        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void getAllBooks_shouldReturnBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("A Farewell to Arms"))
                .andExpect(jsonPath("$[1].title").value("Animal Farm"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void getBookById_shouldReturnBook() throws Exception {
        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("A Farewell to Arms"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void getBookById_shouldReturnNotFound() throws Exception {
        given(bookRepository.findById(8L)).willReturn(Optional.empty());

        mockMvc.perform(get("/book/8"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testCreateBook() throws Exception {
        Book newBook = new Book();
        newBook.setId(3L);
        newBook.setTitle("Book Three");
        newBook.setAuthor("Author Three");
        newBook.setIsbn("123123123");
        newBook.setPublicationYear(2022);
        newBook.setPrice(20.0);

        given(bookRepository.save(newBook)).willReturn(newBook);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":3,\"title\":\"Book Three\",\"author\":\"Author Three\",\"isbn\":\"1122334455\",\"publicationYear\":2023,\"price\":20.0}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Book Three"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testDeleteBook() throws Exception {
        Book bookToDelete = new Book();
        bookToDelete.setId(15L);

        given(bookRepository.findById(15L)).willReturn(Optional.of(bookToDelete));

        mockMvc.perform(delete("/book/15"))
                .andExpect(status().isNoContent());
    }
}

package bookstore.backend.domain;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private String publicationYear;
    private String price;

    public Book(String title, String author, String isbn, String publicationYear, String price) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getpublicationYear() {
        return publicationYear;
    }

    public String getPrice() {
        return price;
    }
}

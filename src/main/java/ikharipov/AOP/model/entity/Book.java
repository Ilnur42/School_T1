package ikharipov.AOP.model.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "books", schema = "entities")
public class Book extends CurrentEntity {

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author", referencedColumnName = "id")
    private Author author;

    @Column(name = "year_of_published")
    private int yearOfPublished;

    @Column(name = "cost")
    private int cost;

    @Column(name = "number_of_pages")
    private int numberOfPages;

    @Column(name = "count")
    private int count;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_shop", referencedColumnName = "id")
    private BookShop bookShop;

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getYearOfPublished() {
        return yearOfPublished;
    }

    public void setYearOfPublished(int yearOfPublished) {
        this.yearOfPublished = yearOfPublished;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BookShop getBookShop() {
        return bookShop;
    }

    public void setBookShop(BookShop bookShop) {
        this.bookShop = bookShop;
    }
    private Book(Book.Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setAuthor(builder.author);
        setYearOfPublished(builder.yearOfPublished);
        setCost(builder.cost);
        setNumberOfPages(builder.numberOfPages);
        setCount(builder.count);
        setBookShop(builder.bookShop);
        setCreated(builder.created);
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private Author author;
        private int yearOfPublished;
        private int cost;
        private int numberOfPages;
        private int count;
        private BookShop bookShop;
        private Date created;

        public Builder() {
        }

        public Book.Builder withId(UUID val) {
            id = val;
            return this;
        }

        public Book.Builder withName(String val) {
            name = val;
            return this;
        }

        public Book.Builder withAuthor(Author val) {
            author = val;
            return this;
        }

        public Book.Builder withYearOfPublished(int val) {
            yearOfPublished = val;
            return this;
        }

        public Book.Builder withCost(int val) {
            cost = val;
            return this;
        }

        public Book.Builder withNumberOfPages(int val) {
            numberOfPages = val;
            return this;
        }

        public Book.Builder withCount(int val) {
            count = val;
            return this;
        }

        public Book.Builder withBookShop(BookShop val) {
            bookShop = val;
            return this;
        }

        public Book.Builder withCreated(Date val) {
            created = val;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}

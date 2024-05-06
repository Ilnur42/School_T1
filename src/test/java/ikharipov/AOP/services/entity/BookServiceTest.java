package ikharipov.AOP.services.entity;

import ikharipov.AOP.model.entity.Author;
import ikharipov.AOP.model.entity.Book;
import ikharipov.AOP.model.entity.BookShop;
import ikharipov.AOP.repository.entity_repositories.BookRepository;
import ikharipov.AOP.services.entity_services.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {BookService.class})
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class BookServiceTest {

    @MockBean
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @Test
    public void existingBookIdTest() {
        UUID bookId = UUID.randomUUID();
        Book book = new Book.Builder()
                .withId(bookId)
                .withName("book_name")
                .withAuthor(new Author())
                .withNumberOfPages(256)
                .withBookShop(new BookShop())
                .withCost(584)
                .withYearOfPublished(1984)
                .withNumberOfPages(511)
                .build();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book actualBook = bookService.getBookById(bookId);
        assertNotNull(actualBook);
        assertEquals(book, actualBook);
    }

    @Test
    public void nonExistingBookIdTest() {
        UUID bookId = UUID.randomUUID();
        when(bookRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            bookService.getBookById(bookId);
        });
    }
}

package ikharipov.AOP.controllers.entity;

import ikharipov.AOP.generators.BookGenerator;
import ikharipov.AOP.model.entity.Book;
import ikharipov.AOP.services.entity_services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookGenerator bookGenerator;

    @Operation(summary = "Получение информации о книге по ее идентификатору")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение информации о книге",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Book.class))
    )
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable("bookId") String bookId) {
        return bookService.getBookById(UUID.fromString(bookId));
    }

    @Operation(summary = "Получение книги по ее названию")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение информации о книге",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Book.class))
    )
    @GetMapping("by_name/{bookName}")
    public Book getBook(@PathVariable("bookName") String bookName) {
        return bookService.getBookByName(bookName);
    }

    @Operation(summary = "Получение списка книг по идентификатору книжного магазина")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение списка книг",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Book.class))
    )
    @GetMapping("/by_bookShop/{bookShopId}")
    public List<Book> getBooksByBookShop(@PathVariable("bookShopId") String bookShopId) {
        return bookService.getBooksByBookShopId(UUID.fromString(bookShopId));
    }

    @Operation(summary = "Обновление количества книг по идентификатору книги")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное обновление количества книг"
    )
    @PostMapping("/update/{bookId}")
    public void updateBooksCountById(@RequestBody Integer count, @PathVariable("bookId") String bookId) {
        bookService.updateBookCountById(UUID.fromString(bookId), count);
    }

    @Operation(summary = "Добавление новой книги")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное добавление новой книги"
    )
    @PostMapping("/add")
    public void addNewBook(@RequestBody Book book) {
        bookService.addNewBook(book);
    }

    @Operation(summary = "Генерация случайной книги")
    @ApiResponse(
            responseCode = "200",
            description = "Успешная генерация случайной книги"
    )
    @PostMapping("/generate")
    public void generateRandomBook() {
        bookService.addNewBook(bookGenerator.generateBook());
    }

    @Operation(summary = "Удаление книги по ее идентификатору")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное удаление книги"
    )
    @PostMapping("/remove/{bookId}")
    public void removeAuthorById(@PathVariable("bookId") String bookId) {
        bookService.removeBookById(UUID.fromString(bookId));
    }
}
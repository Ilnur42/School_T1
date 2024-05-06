package ikharipov.AOP.controllers.entity;

import ikharipov.AOP.generators.BookShopGenerator;
import ikharipov.AOP.model.entity.BookShop;
import ikharipov.AOP.services.entity_services.BookShopService;
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
@RequestMapping("api/v1/bookShop")
public class BookShopController {

    @Autowired
    private BookShopService bookShopService;
    @Autowired
    private BookShopGenerator bookShopGenerator;

    @Operation(summary = "Получение информации о книжном магазине по его идентификатору")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение информации о книжном магазине",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BookShop.class))
    )
    @GetMapping("/{bookShopId}")
    public BookShop getBookShopById(@PathVariable("bookShopId") String bookShopId) {
        return bookShopService.getBookShopById(UUID.fromString(bookShopId));
    }

    @Operation(summary = "Получение списка книжных магазинов по их названию")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение списка книжных магазинов",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = BookShop.class))
    )
    @GetMapping("by_name/{bookShopName}")
    public List<BookShop> getBookShopsByName(@PathVariable("bookShopName") String bookShopName) {
        return bookShopService.getBookShopsByName(bookShopName);
    }

    @Operation(summary = "Обновление адреса книжного магазина по его идентификатору")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное обновление адреса книжного магазина"
    )
    @PostMapping("/update/{bookShopId}")
    public void updateBookShopAddressById(@RequestBody String address, @PathVariable("bookShopId") String bookShopId) {
        bookShopService.updateBookShopAddressById(UUID.fromString(bookShopId), address);
    }

    @Operation(summary = "Добавление нового книжного магазина")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное добавление нового книжного магазина"
    )
    @PostMapping("/add")
    public void addNewBookShop(@RequestBody BookShop bookShop) {
        bookShopService.addNewBookShop(bookShop);
    }

    @Operation(summary = "Генерация случайного книжного магазина")
    @ApiResponse(
            responseCode = "200",
            description = "Успешная генерация случайного книжного магазина"
    )
    @PostMapping("/generate")
    public void generateRandomBookShop() {
        bookShopService.addNewBookShop(bookShopGenerator.generateBookShop());
    }

    @Operation(summary = "Удаление книжного магазина по его идентификатору")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное удаление книжного магазина"
    )
    @PostMapping("/remove/{bookShopId}")
    public void removeAuthorById(@PathVariable("bookShopId") String bookShopId) {
        bookShopService.removeBookShopById(UUID.fromString(bookShopId));
    }
}
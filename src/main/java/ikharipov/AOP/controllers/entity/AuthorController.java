package ikharipov.AOP.controllers.entity;

import ikharipov.AOP.generators.AuthorGenerator;
import ikharipov.AOP.model.entity.Author;
import ikharipov.AOP.services.entity_services.AuthorService;
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
@RequestMapping("api/v1/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorGenerator authorGenerator;

    @Operation(summary = "Получение информации об авторе по его идентификатору")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение информации об авторе",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Author.class))
    )
    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable("authorId") String authorId) {
        return authorService.getAuthorById(UUID.fromString(authorId));
    }

    @Operation(summary = "Получение списка всех авторов с указанным именем")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение списка всех авторов",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Author.class))
    )
    @GetMapping("/by_name/{authorName}")
    public List<Author> getAllAuthorsByName(@PathVariable("authorName") String authorName) {
        return authorService.getAuthorsByName(authorName);
    }

    @Operation(summary = "Обновление возраста автора по его идентификатору")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное обновление возраста автора"
    )
    @PostMapping("/update/{authorId}")
    public void updateAuthorAge(@RequestBody Integer age, @PathVariable("authorId") String authorId) {
        authorService.updateAuthorAgeById(UUID.fromString(authorId), age);
    }

    @Operation(summary = "Добавление нового автора")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное добавление нового автора"
    )
    @PostMapping("/add")
    public void addNewAuthor(@RequestBody Author author) {
        authorService.addNewAuthor(author);
    }

    @Operation(summary = "Генерация случайного автора")
    @ApiResponse(
            responseCode = "200",
            description = "Успешная генерация случайного автора"
    )
    @PostMapping("/generate")
    public void generateRandomAuthor() {
        authorService.addNewAuthor(authorGenerator.generateAuthor());
    }

    @Operation(summary = "Удаление автора по его идентификатору")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное удаление автора"
    )
    @PostMapping("/remove/{authorId}")
    public void removeAuthorById(@PathVariable("authorId") String authorId) {
        authorService.removeAuthorById(UUID.fromString(authorId));
    }
}
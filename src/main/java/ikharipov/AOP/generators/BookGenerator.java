package ikharipov.AOP.generators;

import ikharipov.AOP.model.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Класс служит для кастомной генерации книги с случайными данными.
 */
@Component
public class BookGenerator {

    @Autowired
    private BookShopGenerator bookShopGenerator;
    @Autowired
    private AuthorGenerator authorGenerator;

    private static final List<String> BOOKS_NAME = List.of("Ведьмак", "Норвежский лес", "Алые паруса", "Облако в штанах", "Автостопом по Галактике", "Шум времени");
    public Book generateBook() {
        return new Book.Builder()
                .withName(getRandomBookName())
                .withAuthor(authorGenerator.generateAuthor())
                .withYearOfPublished(getRandomNumber(1920, 2000))
                .withNumberOfPages(getRandomNumber(100, 1000))
                .withBookShop(bookShopGenerator.generateBookShop())
                .withCost(getRandomNumber(200, 1000))
                .withCount(getRandomNumber(2, 100))
                .build();
    }
    private static String getRandomBookName() {
        Random random = new Random();
        int randomIndex = random.nextInt(BOOKS_NAME.size());
        return BOOKS_NAME.get(randomIndex);
    }

    private static int getRandomNumber(int minNumber, int maxNumber) {
        Random random = new Random();
        return random.nextInt(maxNumber - minNumber + 1) + minNumber;
    }
}

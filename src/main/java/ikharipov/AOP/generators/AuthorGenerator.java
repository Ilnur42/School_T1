package ikharipov.AOP.generators;

import ikharipov.AOP.model.entity.Author;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Класс служит для кастомной генерации автора с случайными данными.
 */
@Component
public class AuthorGenerator {

    private static final List<String> FIRST_NAMES = List.of("Анджей", "Харуки", "Сергей", "Владимир", "Дуглас", "Осип");
    private static final List<String> LAST_NAMES = List.of("Сапковский", "Мураками", "Есенин", "Маяковский", "Адамс", "Мандельштам");
    private static final List<String> COUNTRIES = List.of("Польша", "Япония", "СССР", "Англия");

    public Author generateAuthor() {
        return new Author.Builder()
                .withFirstName(getRandomFirstName())
                .withLastName(getRandomLastName())
                .withAge(getRandomAge())
                .withCountry(getRandomCountry())
                .build();
    }

    private static String getRandomFirstName() {
        Random random = new Random();
        int randomIndex = random.nextInt(FIRST_NAMES.size());
        return FIRST_NAMES.get(randomIndex);
    }

    private static String getRandomLastName() {
        Random random = new Random();
        int randomIndex = random.nextInt(LAST_NAMES.size());
        return LAST_NAMES.get(randomIndex);
    }

    private static int getRandomAge() {
        int minAge = 30;
        int maxAge = 70;
        Random random = new Random();
        return random.nextInt(maxAge - minAge + 1) + minAge;
    }

    private static String getRandomCountry() {
        Random random = new Random();
        int randomIndex = random.nextInt(COUNTRIES.size());
        return COUNTRIES.get(randomIndex);
    }
}

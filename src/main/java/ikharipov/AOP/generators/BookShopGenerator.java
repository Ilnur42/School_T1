package ikharipov.AOP.generators;

import ikharipov.AOP.model.entity.BookShop;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

/**
 * Класс служит для кастомной генерации книжного магазина с случайными данными.
 */
@Component
public class BookShopGenerator {

    private static final List<String> BOOK_SHOP_NAMES = List.of("Black Books", "Strand Bookstore", "Books of Wonder", "Shakespeare and Company", "Powell's City of Books", "The Strand");
    private static final List<String> ADDRESSES = List.of("13 Little Bevan Street", "221B Бейкер-стрит", "12 Grimmauld Place, London", "37 Rue de la Bucherie, 75005 Paris", "123 Джейн Уэй, Спрингфилд", "828 Broadway, New York");

    public BookShop generateBookShop() {
        return new BookShop.Builder()
                .withName(getRandomBookShopName())
                .withAddress(getRandomAddress())
                .withPhoneNumber(getRandomPhoneNumber())
                .build();
    }

    private static String getRandomBookShopName() {
        Random random = new Random();
        int randomIndex = random.nextInt(BOOK_SHOP_NAMES.size());
        return BOOK_SHOP_NAMES.get(randomIndex);
    }

    private static String getRandomAddress() {
        Random random = new Random();
        int randomIndex = random.nextInt(ADDRESSES.size());
        return ADDRESSES.get(randomIndex);
    }

    private static String getRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder phoneNumber = new StringBuilder();
        phoneNumber.append("+");
        phoneNumber.append(random.nextInt(9) + 1);
        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }
}

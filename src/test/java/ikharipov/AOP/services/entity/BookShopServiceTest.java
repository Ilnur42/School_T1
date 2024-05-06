package ikharipov.AOP.services.entity;

import ikharipov.AOP.model.entity.BookShop;
import ikharipov.AOP.repository.entity_repositories.BookShopRepository;
import ikharipov.AOP.services.entity_services.BookShopService;
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

@SpringBootTest(classes = {BookShopService.class})
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class BookShopServiceTest {

    @MockBean
    private BookShopRepository bookShopRepository;
    @Autowired
    private BookShopService bookShopService;

    @Test
    public void existingBookShopIdTest() {
        UUID bookShopId = UUID.randomUUID();
        BookShop bookShop = new BookShop.Builder()
                .withId(bookShopId)
                .withName("bookShop_name")
                .withPhoneNumber("+41235421521")
                .withAddress("Address")
                .build();

        when(bookShopRepository.findById(bookShopId)).thenReturn(Optional.of(bookShop));
        BookShop actualBookShop = bookShopService.getBookShopById(bookShopId);
        assertNotNull(actualBookShop);
        assertEquals(bookShop, actualBookShop);
    }

    @Test
    public void nonExistingBookShopIdTest() {
        UUID bookShopId = UUID.randomUUID();
        when(bookShopRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            bookShopService.getBookShopById(bookShopId);
        });
    }
}

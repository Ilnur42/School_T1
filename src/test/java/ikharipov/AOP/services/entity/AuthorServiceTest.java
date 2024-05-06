package ikharipov.AOP.services.entity;

import ikharipov.AOP.model.entity.Author;
import ikharipov.AOP.repository.entity_repositories.AuthorRepository;
import ikharipov.AOP.services.entity_services.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {AuthorService.class})
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class AuthorServiceTest {

    @MockBean
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;

    @Test
    public void existingAuthorIdTest() {
        UUID authorId = UUID.randomUUID();
        Author expectedAuthor = new Author.Builder()
                .withId(authorId)
                .withFirstName("Name")
                .withLastName("LastName")
                .withCountry("Country")
                .withAge(25).build();
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(expectedAuthor));

        Author actualAuthor = authorService.getAuthorById(authorId);

        assertNotNull(actualAuthor);
        assertEquals(expectedAuthor, actualAuthor);
    }

    @Test
    public void nonExistingAuthorIdTest() {
        UUID authorId = UUID.randomUUID();
        when(authorRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            authorService.getAuthorById(authorId);
        });
    }
}

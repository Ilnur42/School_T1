package ikharipov.AOP.services.entity_services;

import ikharipov.AOP.aop.annotations.TrackTime;
import ikharipov.AOP.exceptions.ErrorCode;
import ikharipov.AOP.exceptions.SyncMethodExecutionException;
import ikharipov.AOP.model.entity.Author;
import ikharipov.AOP.repository.entity_repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для управления информацией об авторах.
 */
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Получает информацию об авторе по его идентификатору.
     *
     * @param authorId Идентификатор автора.
     * @return Объект Author.
     * @throws SyncMethodExecutionException если автор с указанным идентификатором не найден.
     */
    @TrackTime
    public Author getAuthorById(UUID authorId) {
        return authorRepository.findById(authorId).orElseThrow(() ->
                new SyncMethodExecutionException(MessageFormat.format("По идентификатору {0} не найден автор", authorId.toString()), ErrorCode.ENTITY_NOT_FOUND));
    }

    /**
     * Получает список авторов по их имени.
     *
     * @param firstName Имя автора.
     * @return Список авторов.
     */
    @TrackTime
    public List<Author> getAuthorsByName(String firstName) {
        return authorRepository.findAuthorByFirstName(firstName);
    }


    /**
     * Добавляет нового автора.
     *
     * @param author Новый автор.
     */
    @TrackTime
    @Transactional
    public void addNewAuthor(Author author) {
        authorRepository.save(author);
    }

    /**
     * Обновляет возраст автора по его идентификатору.
     *
     * @param authorId Идентификатор автора.
     * @param age      Новый возраст.
     */
    @TrackTime
    @Transactional
    public void updateAuthorAgeById(UUID authorId, int age) {
        authorRepository.updateAuthorAgeById(authorId, age);
    }

    /**
     * Удаляет автора по его идентификатору.
     *
     * @param authorId Идентификатор автора.
     */
    @TrackTime
    @Transactional
    public void removeAuthorById(UUID authorId) {
        authorRepository.removeAuthorById(authorId);
    }
}
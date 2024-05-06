package ikharipov.AOP.services.entity_services;

import ikharipov.AOP.aop.annotations.TrackTime;
import ikharipov.AOP.exceptions.ErrorCode;
import ikharipov.AOP.exceptions.SyncMethodExecutionException;
import ikharipov.AOP.model.entity.Book;
import ikharipov.AOP.repository.entity_repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для управления информацией о книгах.
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Получает информацию о книге по её идентификатору.
     *
     * @param bookId Идентификатор книги.
     * @return Объект Book.
     * @throws SyncMethodExecutionException если книга с указанным идентификатором не найдена.
     */
    @TrackTime
    public Book getBookById(UUID bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new SyncMethodExecutionException(MessageFormat.format("По идентификатору {0} книга не найдена", bookId.toString()), ErrorCode.ENTITY_NOT_FOUND));
    }

    /**
     * Получает информацию о книге по её названию.
     *
     * @param bookName Название книги.
     * @return Объект Book.
     * @throws SyncMethodExecutionException если книга с указанным названием не найдена.
     */
    @TrackTime
    public Book getBookByName(String bookName) {
        return bookRepository.findBooksByName(bookName).stream().findFirst().orElseThrow(() ->
                new SyncMethodExecutionException(MessageFormat.format("Не найдено книг по наименованию {0}", bookName), ErrorCode.ENTITY_NOT_FOUND));
    }

    /**
     * Получает список книг для заданного книжного магазина.
     *
     * @param bookShopId Идентификатор книжного магазина.
     * @return Список книг.
     * @throws SyncMethodExecutionException если для данного магазина не найдено книг.
     */
    @TrackTime
    public List<Book> getBooksByBookShopId(UUID bookShopId) {
        List<Book> books = bookRepository.findBooksByBookShopId(bookShopId);
        if (books.isEmpty()) {
            throw new SyncMethodExecutionException("У данного магазина не найдено книг", ErrorCode.ENTITY_NOT_FOUND);
        }
        return books;
    }

    /**
     * Добавляет новую книгу.
     *
     * @param book Новая книга.
     */
    @TrackTime
    @Transactional
    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * Обновляет количество экземпляров книги по её идентификатору.
     *
     * @param bookId Идентификатор книги.
     * @param count  Новое количество экземпляров.
     */
    @TrackTime
    @Transactional
    public void updateBookCountById(UUID bookId, int count) {
        bookRepository.updateBooksCountById(bookId, count);
    }

    /**
     * Удаляет книгу по её идентификатору.
     *
     * @param bookId Идентификатор книги.
     */
    @TrackTime
    @Transactional
    public void removeBookById(UUID bookId) {
        bookRepository.removeBookById(bookId);
    }
}

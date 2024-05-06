package ikharipov.AOP.services.entity_services;

import ikharipov.AOP.aop.annotations.TrackTime;
import ikharipov.AOP.exceptions.ErrorCode;
import ikharipov.AOP.exceptions.SyncMethodExecutionException;
import ikharipov.AOP.model.entity.BookShop;
import ikharipov.AOP.repository.entity_repositories.BookShopRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для управления информацией о книжных магазинах.
 */
@Service
public class BookShopService {

    @Autowired
    private BookShopRepository bookShopRepository;

    /**
     * Получает информацию о книжном магазине по его идентификатору.
     *
     * @param bookShopId Идентификатор книжного магазина.
     * @return Объект {@link BookShop}.
     * @throws SyncMethodExecutionException если книжный магазин с указанным идентификатором не найден.
     */
    @TrackTime
    public BookShop getBookShopById(UUID bookShopId) {
        return bookShopRepository.findById(bookShopId).orElseThrow(() ->
                new SyncMethodExecutionException(MessageFormat.format("По идентифиактору {0} не найдено книжных магазинов", bookShopId), ErrorCode.ENTITY_NOT_FOUND));
    }

    /**
     * Находит список книжных магазинов по их названию.
     *
     * @param bookShopName Название книжного магазина.
     * @return Список книжных магазинов с указанным названием.
     */
    @TrackTime
    public List<BookShop> getBookShopsByName(String bookShopName) {
        return bookShopRepository.findBookShopsByName(bookShopName);
    }

    /**
     * Добавляет новый книжный магазин.
     *
     * @param bookShop Новый книжный магазин.
     */
    @TrackTime
    @Transactional
    public void addNewBookShop(BookShop bookShop) {
        bookShopRepository.save(bookShop);
    }

    /**
     * Обновляет адрес книжного магазина по его идентификатору.
     *
     * @param bookShopId Идентификатор книжного магазина.
     * @param address    Новый адрес.
     */
    @TrackTime
    @Transactional
    public void updateBookShopAddressById(UUID bookShopId, String address) {
        bookShopRepository.updateBookShopAddressById(bookShopId, address);
    }

    /**
     * Удаляет книжный магазин по его идентификатору.
     *
     * @param bookShopId Идентификатор книжного магазина.
     */
    @TrackTime
    @Transactional
    public void removeBookShopById(UUID bookShopId) {
        bookShopRepository.removeBookShopById(bookShopId);
    }
}

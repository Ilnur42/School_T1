package ikharipov.AOP.services.job_services;

import ikharipov.AOP.aop.annotations.TrackAsyncTime;
import ikharipov.AOP.exceptions.JobExecutionException;
import ikharipov.AOP.model.entity.BookShop;
import ikharipov.AOP.repository.entity_repositories.BookShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * Сервис содержит различные джобы для работы с сущностью книжный магазин.
 */
@Service
public class BookShopJobService {

    private static final Logger logger = LoggerFactory.getLogger(BookShopJobService.class);

    @Autowired
    private BookShopRepository bookShopRepository;

    /**
     * Джоба получения информации о количестве книжных магазинов в бд.
     */
    @TrackAsyncTime
    public void getBookShopsInfoJob() {
        try {
            int bookShopCount = bookShopRepository.findAll().size();
            logger.info(MessageFormat.format("В настоящий момент в базе хранится информация по {0} книжным магазинам", bookShopCount));
        } catch (JobExecutionException ex) {
            throw new JobExecutionException("Произошла ошибка при работе джобы получения информации о книжных магазинах", ex);
        }
    }

    /**
     * Джоба удаления устаревшей информации по книжным магазинам в бд.
     *
     * @param days Количество дней, определяющее период времени, за который считается информация о книжном магазине устаревшей.
     */
    @TrackAsyncTime
    @Transactional
    public void removeOldBookShopInfo(long days) {
        try {
            Date daysAgo = new Date(System.currentTimeMillis() - days * 24 * 60 * 60 * 1000);
            List<BookShop> bookShopsToRemove = bookShopRepository.findBookShopByCreatedBefore(daysAgo);
            bookShopRepository.deleteAll(bookShopsToRemove);
        } catch (JobExecutionException ex) {
            throw new JobExecutionException("Произошла ошибка при работе джобы по удалению устаревшей информации о книжных магазинах", ex);
        }
    }
}

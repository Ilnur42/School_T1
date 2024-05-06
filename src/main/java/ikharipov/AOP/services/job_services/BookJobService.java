package ikharipov.AOP.services.job_services;

import ikharipov.AOP.aop.annotations.TrackAsyncTime;
import ikharipov.AOP.exceptions.JobExecutionException;
import ikharipov.AOP.model.entity.Book;
import ikharipov.AOP.repository.entity_repositories.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * Сервис содержит различные джобы для работы с сущностью Книга.
 */
@Service
public class BookJobService {

    private static final Logger logger = LoggerFactory.getLogger(BookJobService.class);

    @Autowired
    private BookRepository bookRepository;

    /**
     * Джоба получения информации о количестве книг магазинов в бд.
     */
    @TrackAsyncTime
    public void getBooksInfoJob() {
        try {
            int booksCount = bookRepository.findAll().size();
            logger.info(MessageFormat.format("В настоящий момент в базе хранится информация по {0} книгам", booksCount));
        } catch (JobExecutionException ex) {
            throw new JobExecutionException("Произошла ошибка при работе джобы получения информации о книгах", ex);
        }
    }

    /**
     * Джоба удаления устаревшей информации по книгам в бд.
     *
     * @param days Количество дней, определяющее период времени, за который считается информация о книге устаревшей.
     */
    @TrackAsyncTime
    @Transactional
    public void removeOldBooksInfo(long days) {
        try {
            Date daysAgo = new Date(System.currentTimeMillis() - days * 24 * 60 * 60 * 1000);
            List<Book> booksToRemove = bookRepository.findBooksByCreatedBefore(daysAgo);
            bookRepository.deleteAll(booksToRemove);
        } catch (JobExecutionException ex) {
            throw new JobExecutionException("Произошла ошибка при работе джобы по удалению устаревшей информации о книгах", ex);
        }
    }
}

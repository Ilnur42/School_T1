package ikharipov.AOP.services.job_services;

import ikharipov.AOP.aop.annotations.TrackAsyncTime;
import ikharipov.AOP.exceptions.JobExecutionException;
import ikharipov.AOP.model.entity.Author;
import ikharipov.AOP.repository.entity_repositories.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * Сервис содержит различные джобы для работы с сущностью Автор.
 */
@Service
public class AuthorJobService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorJobService.class);

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Джоба получения информации о количестве авторов магазинов в бд.
     */
    @TrackAsyncTime
    public void getAuthorsInfoJob() {
        try {
            int authorsCount = authorRepository.findAll().size();
            logger.info(MessageFormat.format("В настоящий момент в базе хранится информация по {0} авторам", authorsCount));
        } catch (JobExecutionException ex) {
            throw new JobExecutionException("Произошла ошибка при работе джобы получения информации об авторах", ex);
        }
    }

    /**
     * Джоба удаления устаревшей информации по авторам в бд.
     *
     * @param days Количество дней, определяющее период времени, за который считается информация об авторе устаревшей.
     */
    @TrackAsyncTime
    @Transactional
    public void removeOldAuthorInfo(long days) {
        try {
            Date daysAgo = new Date(System.currentTimeMillis() - days * 24 * 60 * 60 * 1000);
            List<Author> authorsToRemove = authorRepository.findAuthorByCreatedBefore(daysAgo);
            authorRepository.deleteAll(authorsToRemove);
        } catch (JobExecutionException ex) {
            throw new JobExecutionException("Произошла ошибка при работе джобы по удалению устаревшей информации об авторах", ex);
        }
    }
}

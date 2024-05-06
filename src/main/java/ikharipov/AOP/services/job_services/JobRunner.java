package ikharipov.AOP.services.job_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Класс, отвечающий за запуск периодических (асинхронных) задач в приложении.
 */
@Component
public class JobRunner {

    @Autowired
    private BookJobService bookJobService;
    @Autowired
    private AuthorJobService authorJobService;
    @Autowired
    private BookShopJobService bookShopJobService;

    /**
     * Периодически запускает задачи по объявлению сведений о книгах, авторах и книжных магазинах.
     * Задачи запускаются по крону из настроек
     */
    @Scheduled(cron = "${cron.expression.job.write}")
    public void autoLoggingActualEntityInfo() {
        bookJobService.getBooksInfoJob();
        authorJobService.getAuthorsInfoJob();
        bookShopJobService.getBookShopsInfoJob();
    }

    /**
     * Периодически запускает задачи по удалению устаревших сведений о книгах, авторах и книжных магазинах.
     * Задачи запускаются по крону из настроек
     */
    @Scheduled(cron = "${cron.expression.job.delete}")
    public void autoDeleteOldEntity() {
        bookJobService.removeOldBooksInfo(3);
        authorJobService.removeOldAuthorInfo(4);
        bookShopJobService.removeOldBookShopInfo(4);
    }
}

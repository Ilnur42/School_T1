package ikharipov.AOP.services.track_time_services;

import ikharipov.AOP.model.tracktime.MethodTimeExecution;
import ikharipov.AOP.model.tracktime.MethodType;
import ikharipov.AOP.repository.tracktime_repositories.TrackTimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для отслеживания времени выполнения методов и хранения этой информации в базе данных.
 */
@Service
public class TrackTimeService {

    @Autowired
    private TrackTimeRepository trackTimeRepository;

    /**
     * Создает объект {@link MethodTimeExecution} и сохраняет его в базе данных.
     *
     * @param methodName          Название метода, время выполнения которого требуется сохранить.
     * @param executionTimeMillis Время выполнения метода в миллисекундах.
     * @param type                Тип метода (синхронный или асинхронный).
     */
    @Transactional
    public void createAndSaveMethodTimeExecution(String methodName, long executionTimeMillis, MethodType type) {
        trackTimeRepository.save(createMethodTimeExecution(methodName, executionTimeMillis, type));
    }

    /**
     * Создает объект MethodTimeExecution.
     *
     * @param methodName          Название метода.
     * @param executionTimeMillis Время выполнения метода в миллисекундах.
     * @param type                Тип метода (синхронный или асинхронный).
     * @return Объект {@link MethodTimeExecution}.
     */
    public MethodTimeExecution createMethodTimeExecution(String methodName, long executionTimeMillis, MethodType type) {
        return new MethodTimeExecution.Builder()
                .withId(UUID.randomUUID())
                .withMethodName(methodName)
                .withExecutionTimeMillis(executionTimeMillis)
                .withCreated(new Date())
                .withMethodType(type)
                .build();
    }

    /**
     * Получает информацию о времени выполнения метода по его идентификатору.
     *
     * @param methodId Идентификатор метода.
     * @return Объект {@link MethodTimeExecution} или null, если метод не найден.
     */
    public MethodTimeExecution getMethodTimeExecutionsById(UUID methodId) {
        return trackTimeRepository.findById(methodId).orElse(null);
    }

    /**
     * Вычисляет среднее время выполнения методов из списка.
     *
     * @param methodTimeExecutions Список {@link MethodTimeExecution}.
     * @return Среднее время выполнения методов в миллисекундах.
     */
    public double getAverageMethodExecutionTime(List<MethodTimeExecution> methodTimeExecutions) {
        return methodTimeExecutions.stream()
                .mapToLong(MethodTimeExecution::getExecutionTimeMillis)
                .average()
                .orElse(0);
    }

    /**
     * Находит максимальное время выполнения методов из списка.
     *
     * @param methodTimeExecutions Список {@link MethodTimeExecution}.
     * @return Максимальное время выполнения методов в миллисекундах.
     */
    public long getMaxMethodExecutionTime(List<MethodTimeExecution> methodTimeExecutions) {
        return methodTimeExecutions.stream()
                .mapToLong(MethodTimeExecution::getExecutionTimeMillis)
                .max()
                .orElse(0);
    }

    /**
     * Находит минимальное время выполнения методов из списка.
     *
     * @param methodTimeExecutions Список {@link MethodTimeExecution}.
     * @return Минимальное время выполнения методов в миллисекундах.
     */
    public long getMinMethodExecutionTime(List<MethodTimeExecution> methodTimeExecutions) {
        return methodTimeExecutions.stream()
                .mapToLong(MethodTimeExecution::getExecutionTimeMillis)
                .min()
                .orElse(0);
    }

    /**
     * Получает список MethodTimeExecution по названию метода.
     *
     * @param methodName Название метода.
     * @return Список {@link MethodTimeExecution}.
     */
    public List<MethodTimeExecution> getMethodTimeExecutionsByName(String methodName) {
        return trackTimeRepository.getAllByMethodName(methodName);
    }
}

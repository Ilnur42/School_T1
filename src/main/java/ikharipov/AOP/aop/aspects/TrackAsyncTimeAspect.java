package ikharipov.AOP.aop.aspects;

import ikharipov.AOP.exceptions.JobExecutionException;
import ikharipov.AOP.model.tracktime.MethodType;
import ikharipov.AOP.services.track_time_services.TrackTimeService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Аспект для работы с временем выполнения асинхронных методов.
 */
@Aspect
@Component
public class TrackAsyncTimeAspect {

    @Autowired
    private TrackTimeService trackTimeService;
    private static final Logger logger = LoggerFactory.getLogger(TrackAsyncTimeAspect.class);

    @Pointcut("@annotation(ikharipov.AOP.aop.annotations.TrackAsyncTime)")
    public void trackAsyncTimePointcut() {
    }

    @Around("trackAsyncTimePointcut()")
    public Object trackAsyncTimeMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info(String.format("Начало выполнения асинхронного метода: %s", methodName));
        long startTime = System.currentTimeMillis();
        Object object;
        try {
            object = proceedingJoinPoint.proceed();
        } catch (Exception ex) {
            logger.error("Произошла ошибка во время выполнения асинхронного метода: " + methodName, ex);
            throw new JobExecutionException("Ошибка во время выполнения асинхронного метода: " + methodName, ex);
        }
        long finishTime = System.currentTimeMillis();
        long executionTime = finishTime - startTime;
        trackTimeService.createAndSaveMethodTimeExecution(methodName, executionTime, MethodType.ASYNC);
        logger.info(String.format("Окончание выполнения асинхронного метода: %s. Время исполнения : %s мс", methodName, executionTime));
        return object;
    }
}
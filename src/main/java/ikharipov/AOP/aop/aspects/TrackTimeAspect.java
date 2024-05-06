package ikharipov.AOP.aop.aspects;

import ikharipov.AOP.exceptions.SyncMethodExecutionException;
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
 * Аспект для работы с временем выполнения синхронных методов.
 */
@Aspect
@Component
public class TrackTimeAspect {

    @Autowired
    private TrackTimeService trackTimeService;
    private static final Logger logger = LoggerFactory.getLogger(TrackTimeAspect.class);

    @Pointcut("@annotation(ikharipov.AOP.aop.annotations.TrackTime)")
    public void trackTimePointcut() {
    }

    @Around("trackTimePointcut()")
    public Object trackTimeMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.info(String.format("Начало выполнения метода: %s", methodName));
        long startTime = System.currentTimeMillis();
        Object object;
        try {
            object = proceedingJoinPoint.proceed();
        } catch (Exception ex) {
            logger.error("Произошла ошибка во время выполнения синхронного метода: " + methodName, ex);
            throw new SyncMethodExecutionException("Ошибка во время выполнения синхронного метода: " + methodName, ex);
        }
        long finishTime = System.currentTimeMillis();
        long executionTime = finishTime - startTime;
        trackTimeService.createAndSaveMethodTimeExecution(methodName, executionTime, MethodType.SYNC);
        logger.info(String.format("Окончание выполнения метода: %s. Время исполнения : %s мс", methodName, executionTime));
        return object;
    }
}
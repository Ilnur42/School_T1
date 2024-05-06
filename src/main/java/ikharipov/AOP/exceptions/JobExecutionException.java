package ikharipov.AOP.exceptions;

/**
 * Исключение для всех джобов приложения.
 */
public class JobExecutionException extends AsyncMethodExecutionException {

    public JobExecutionException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public JobExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}

package ikharipov.AOP.exceptions;

/**
 * Исключение для всех асинхронных методов приложения.
 */
public class AsyncMethodExecutionException extends RuntimeApplicationException {
    public AsyncMethodExecutionException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public AsyncMethodExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
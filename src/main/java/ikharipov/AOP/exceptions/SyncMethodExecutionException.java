package ikharipov.AOP.exceptions;

/**
 * Исключение для всех синхронных методов приложения.
 */
public class SyncMethodExecutionException extends RuntimeApplicationException {

    public SyncMethodExecutionException(String message) {
        super(message);
    }
    public SyncMethodExecutionException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public SyncMethodExecutionException(String message, Throwable cause) {
        super(message, cause);
    }
}
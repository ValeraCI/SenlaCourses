package framework.exceptions;

public class PrivateMethodException extends RuntimeException {
    public PrivateMethodException() {
    }

    public PrivateMethodException(String message) {
        super(message);
    }

    public PrivateMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public PrivateMethodException(Throwable cause) {
        super(cause);
    }
}

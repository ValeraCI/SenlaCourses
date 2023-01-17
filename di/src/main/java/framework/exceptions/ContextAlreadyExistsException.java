package framework.exceptions;

public class ContextAlreadyExistsException extends RuntimeException{
    public ContextAlreadyExistsException() {
    }

    public ContextAlreadyExistsException(String message) {
        super(message);
    }

    public ContextAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContextAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}

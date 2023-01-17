package framework.exceptions;

public class ContextNotCreatedException extends RuntimeException {
    public ContextNotCreatedException() {
    }

    public ContextNotCreatedException(String message) {
        super(message);
    }

    public ContextNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContextNotCreatedException(Throwable cause) {
        super(cause);
    }
}

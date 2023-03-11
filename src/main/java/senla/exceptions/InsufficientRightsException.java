package senla.exceptions;

public class InsufficientRightsException extends RuntimeException {
    public InsufficientRightsException() {
    }

    public InsufficientRightsException(String message) {
        super(message);
    }

    public InsufficientRightsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientRightsException(Throwable cause) {
        super(cause);
    }
}

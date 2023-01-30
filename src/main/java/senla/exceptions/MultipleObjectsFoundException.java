package senla.exceptions;

public class MultipleObjectsFoundException extends RuntimeException{
    public MultipleObjectsFoundException() {
    }

    public MultipleObjectsFoundException(String message) {
        super(message);
    }

    public MultipleObjectsFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleObjectsFoundException(Throwable cause) {
        super(cause);
    }
}

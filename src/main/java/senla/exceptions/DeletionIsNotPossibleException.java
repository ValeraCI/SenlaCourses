package senla.exceptions;

public class DeletionIsNotPossibleException extends RuntimeException{
    public DeletionIsNotPossibleException() {
    }

    public DeletionIsNotPossibleException(String message) {
        super(message);
    }

    public DeletionIsNotPossibleException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeletionIsNotPossibleException(Throwable cause) {
        super(cause);
    }
}

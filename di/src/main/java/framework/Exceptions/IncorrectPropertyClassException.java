package framework.Exceptions;

public class IncorrectPropertyClassException extends RuntimeException{
    public IncorrectPropertyClassException() {
    }

    public IncorrectPropertyClassException(String message) {
        super(message);
    }

    public IncorrectPropertyClassException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPropertyClassException(Throwable cause) {
        super(cause);
    }
}

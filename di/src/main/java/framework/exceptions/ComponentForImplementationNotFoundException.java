package framework.exceptions;

public class ComponentForImplementationNotFoundException extends RuntimeException{
    public ComponentForImplementationNotFoundException() {
    }

    public ComponentForImplementationNotFoundException(String message) {
        super(message);
    }

    public ComponentForImplementationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentForImplementationNotFoundException(Throwable cause) {
        super(cause);
    }
}

package senla.Exceptions;

public class ObjectsNotLinkedException extends RuntimeException{
    public ObjectsNotLinkedException() {
    }

    public ObjectsNotLinkedException(String message) {
        super(message);
    }

    public ObjectsNotLinkedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectsNotLinkedException(Throwable cause) {
        super(cause);
    }
}

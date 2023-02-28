package senla.exceptions;

public class JsonDeserializeException extends RuntimeException {
    public JsonDeserializeException() {
    }

    public JsonDeserializeException(String message) {
        super(message);
    }

    public JsonDeserializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonDeserializeException(Throwable cause) {
        super(cause);
    }
}

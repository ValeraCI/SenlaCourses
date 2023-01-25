package senla.exceptions;

public class JsonSerialazeException extends RuntimeException{
    public JsonSerialazeException() {
    }

    public JsonSerialazeException(String message) {
        super(message);
    }

    public JsonSerialazeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonSerialazeException(Throwable cause) {
        super(cause);
    }
}

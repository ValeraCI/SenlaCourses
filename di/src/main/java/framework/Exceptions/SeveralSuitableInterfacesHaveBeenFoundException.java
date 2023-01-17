package framework.Exceptions;

public class SeveralSuitableInterfacesHaveBeenFoundException extends RuntimeException{
    public SeveralSuitableInterfacesHaveBeenFoundException() {
    }

    public SeveralSuitableInterfacesHaveBeenFoundException(String message) {
        super(message);
    }

    public SeveralSuitableInterfacesHaveBeenFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeveralSuitableInterfacesHaveBeenFoundException(Throwable cause) {
        super(cause);
    }
}

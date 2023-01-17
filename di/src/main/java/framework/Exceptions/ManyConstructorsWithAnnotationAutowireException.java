package framework.Exceptions;

public class ManyConstructorsWithAnnotationAutowireException extends RuntimeException{
    public ManyConstructorsWithAnnotationAutowireException() {
    }

    public ManyConstructorsWithAnnotationAutowireException(String message) {
        super(message);
    }

    public ManyConstructorsWithAnnotationAutowireException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManyConstructorsWithAnnotationAutowireException(Throwable cause) {
        super(cause);
    }
}

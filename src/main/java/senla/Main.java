package senla;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.configuration.Application;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
    }
}

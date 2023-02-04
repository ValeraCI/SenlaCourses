package senla;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.configuration.Application;
import senla.controllers.AccountController;
import senla.dto.CreateAccountDataDto;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        AccountController accountController = context.getBean(AccountController.class);

        accountController.add("{\"nickname\":\"Oleg1\",\"email\":\"Oleg1@gmail.com\",\"password\":\"1111Oleg\"}");

        //System.out.println(accountController.getById(1));
        //System.out.println(accountController.getById(2));
        //System.out.println(accountController.getById(10));

        //accountController.deleteById(668);

        //System.out.println(accountController.getByEmail("cidikvalera@gmail.com"));
    }
}

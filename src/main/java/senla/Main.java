package senla;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import senla.configuration.Application;
import senla.controllers.AccountController;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        AccountController accountController = context.getBean(AccountController.class);

        List<Thread> threadList = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            threadList.add(new TestThread("{\"nickname\":\"nickname" + i + "\",\"email\":\"test@gmail.com" + i + "\"," +
                    "\"password\":\"1234\"}",
                    accountController));
            threadList.get(i).start();
        }
        System.out.println(accountController.getById(1));

        for(int i = 0; i < 20; i++) {
            try {
                threadList.get(i).join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        context.close();
    }
}

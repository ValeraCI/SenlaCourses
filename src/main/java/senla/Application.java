package senla;

import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.integration.spring.SpringLiquibase;
import org.postgresql.ds.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import senla.controllers.AccountController;


@Configuration
@ComponentScan
@PropertySource("application.properties")
@EnableAspectJAutoProxy
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Value("${db.url}")
    String url;
    @Value("${db.username}")
    String username;
    @Value("${db.password}")
    String password;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);

        AccountController accountController = context.getBean(AccountController.class);

        for(int i = 1; i < 20; i++){
            new TestThread("{\"nickname\":\"nickname" + i + "\",\"email\":\"test@gmail.com" + i + "\"," +
                    "\"password\":\"1234\"}",
                    accountController).start();
        }
        System.out.println(accountController.getById(1));
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public SpringLiquibase springLiquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("changelog.xml");
        return springLiquibase;
    }
}

package senla.test.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("senla")
@Profile("test")
@EnableTransactionManagement
@PropertySource("application.properties")
@SpringJUnitWebConfig
public class Application {
}

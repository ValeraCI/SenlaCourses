package senla.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("senla")
@EnableAspectJAutoProxy
@Import({DaoConfiguration.class})
public class TestConfig {

    public TestConfig(){
        System.out.println("\n\n\n\n\n\nСоздание TestConfig");
    }
}

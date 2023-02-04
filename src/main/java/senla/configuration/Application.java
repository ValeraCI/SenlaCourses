package senla.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("senla")
@PropertySource("application.properties")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Import(DaoConfiguration.class)
public class Application {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
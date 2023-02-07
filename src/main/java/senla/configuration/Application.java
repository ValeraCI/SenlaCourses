package senla.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;


@Configuration
@ComponentScan("senla")
@EnableAspectJAutoProxy
@Import(DaoConfiguration.class)
public class Application {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

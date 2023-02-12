package senla.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan("senla")
@EnableAspectJAutoProxy
@EnableWebMvc
@Import({DaoConfiguration.class})
public class Application implements WebMvcConfigurer {

    public Application(){
        System.out.println("\n\n\n\n\n\nСоздание Application");
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

package senla.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@ComponentScan("senla")
@EnableAspectJAutoProxy
@EnableWebMvc
public class Application implements WebMvcConfigurer {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }


}

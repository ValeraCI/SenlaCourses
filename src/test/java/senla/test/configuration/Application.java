package senla.test.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
@ComponentScan(value = "senla")
@Profile("test")
@EnableTransactionManagement
@PropertySource("application.properties")
public class Application {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }
}

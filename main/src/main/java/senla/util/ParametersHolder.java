package senla.util;

import framework.annotations.Component;
import framework.annotations.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ParametersHolder {
    private static final Logger logger = LoggerFactory.getLogger(ParametersHolder.class);

    @Value(name = "my.param.db", path = "main/src/main/resources/application.properties")
    private String someText;

    public String getSomeText(){
        logger.debug("Method execute started");
        logger.debug("Method getSomeText return '{}'", someText);
        return someText;
    }
}

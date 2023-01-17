package senla.util;

import framework.annotations.Component;
import framework.annotations.Value;

@Component
public class ParametersHolder {
    @Value(name = "my.param.db", path = "main/src/main/resources/application.properties")
    private String someText;

    public String getSomeText(){
        return someText;
    }
}

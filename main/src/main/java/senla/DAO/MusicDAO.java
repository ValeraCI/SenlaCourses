package senla.DAO;

import framework.annotations.Autowire;
import framework.annotations.Component;
import senla.util.ParametersHolder;

@Component
public class MusicDAO implements DAO{
    @Autowire
    private ParametersHolder parametersHolder;

    @Override
    public String execute() {
        return parametersHolder.getSomeText();
    }
}

package senla.dao;

import framework.annotations.Autowired;
import framework.annotations.Component;
import senla.util.ParametersHolder;

@Component
public class MusicDaoImpl implements MusicDao {
    @Autowired
    private ParametersHolder parametersHolder;

    @Override
    public String execute() {
        return parametersHolder.getSomeText();
    }
}

package senla.dao;

import framework.annotations.Autowired;
import framework.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import senla.util.ParametersHolder;

@Component
public class MusicDaoImpl implements MusicDao {
    private static final Logger logger = LoggerFactory.getLogger(MusicDaoImpl.class);

    @Autowired
    private ParametersHolder parametersHolder;

    @Override
    public String execute() {
        logger.debug("Method execute started");
        String text = parametersHolder.getSomeText();
        logger.debug("Method execute return '{}'", text);
        return text;
    }
}

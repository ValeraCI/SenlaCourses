package senla.services;

import framework.annotations.Autowired;
import framework.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import senla.dao.MusicDao;

@Component
public class MusicService implements Service{
    private static final Logger logger = LoggerFactory.getLogger(MusicService.class);
    private MusicDao musicDAO;

    @Autowired
    public void setMusicDAO(MusicDao musicDAO) {
        this.musicDAO = musicDAO;
    }

    @Override
    public String execute(){
        logger.debug("Method execute started");
        String text = musicDAO.execute();
        logger.debug("Method execute return '{}'", text);
        return text;
    }
}

package senla.services;

import framework.annotations.Autowire;
import framework.annotations.Component;
import senla.DAO.DAO;

@Component
public class MusicService implements Service{
    private DAO musicDAO;

    @Autowire
    public void setMusicDAO(DAO musicDAO) {
        this.musicDAO = musicDAO;
    }

    @Override
    public String execute(){
        return musicDAO.execute();
    }
}

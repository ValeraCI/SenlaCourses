package senla.services;

import framework.annotations.Autowired;
import framework.annotations.Component;
import senla.dao.MusicDAO;

@Component
public class MusicService implements Service{
    private MusicDAO musicDAO;

    @Autowired
    public void setMusicDAO(MusicDAO musicDAO) {
        this.musicDAO = musicDAO;
    }

    @Override
    public String execute(){
        return musicDAO.execute();
    }
}

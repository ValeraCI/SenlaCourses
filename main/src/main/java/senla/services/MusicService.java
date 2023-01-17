package senla.services;

import framework.annotations.Autowired;
import framework.annotations.Component;
import senla.dao.MusicDao;

@Component
public class MusicService implements Service{
    private MusicDao musicDAO;

    @Autowired
    public void setMusicDAO(MusicDao musicDAO) {
        this.musicDAO = musicDAO;
    }

    @Override
    public String execute(){
        return musicDAO.execute();
    }
}

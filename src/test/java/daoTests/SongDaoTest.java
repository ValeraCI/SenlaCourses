package daoTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.configuration.Application;
import senla.dao.AccountDao;
import senla.dao.GenreDao;
import senla.dao.SongDao;
import senla.models.Location;
import senla.models.Song;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {Application.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class SongDaoTest {
    @Autowired
    private SongDao songDao;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private AccountDao accountDao;


    private Song createSong(){
        Song song = new Song();

        song.setTitle("TestSong");
        song.setGenre(genreDao.findById(1L));
        Location location = new Location(song, "./somePath");
        song.setLocation(location);
        song.setAuthors(new ArrayList(Arrays.asList(accountDao.findById(1L))));
        return song;
    }

    @Test
    public void CRUDMethods(){
        Song song = createSong();

        Song daoSong = songDao.findById(songDao.save(song));
        Assert.assertEquals(daoSong.getTitle(), song.getTitle());

        daoSong.setTitle("TestSong1");
        songDao.update(daoSong);

        daoSong = songDao.findByTitle("TestSong1").get(0);
        Assert.assertEquals(daoSong.getTitle(), "TestSong1");

        songDao.deleteById(daoSong.getId());
    }

    @Test
    public void findByGenre(){
        List<Song> songList = songDao.findByGenre(genreDao.findById(1L));
        songList.stream().forEach(s -> System.out.println(s.getTitle()));
    }
}

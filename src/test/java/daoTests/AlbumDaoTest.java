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
import senla.dao.AlbumDao;
import senla.models.Album;
import senla.models.Song;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {Application.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class AlbumDaoTest {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private AccountDao accountDao;

    private Album createAlbum(){
        Album album = new Album();

        album.setTitle("TestAlbum");
        album.setCreateDate(new Date());
        album.setCreator(accountDao.findById(1L));

        return album;
    }

    @Test
    public void CRUDMethods(){
        Album album = createAlbum();

        albumDao.save(album);

        Album daoAlbum = albumDao.findByTitle(album.getTitle()).get(0);
        Assert.assertEquals(daoAlbum.getTitle(), album.getTitle());

        daoAlbum.setTitle("TestAlbum123123213213123123");
        albumDao.update(daoAlbum);

        daoAlbum = albumDao.findByTitle("TestAlbum123123213213123123").get(0);
        Assert.assertEquals(daoAlbum.getTitle(), "TestAlbum123123213213123123");

        albumDao.deleteById(daoAlbum.getId());
    }

    @Test
    public void operationsWithSongIn(){
        List<Song> songsIn = albumDao.getSongsIn(1L);
        songsIn.stream().forEach(s -> System.out.println(s.getTitle()));
        Song song = songsIn.get(0);

        albumDao.removeSongIn(1L, song);
        songsIn = albumDao.getSongsIn(1L);
        songsIn.stream().forEach(s -> System.out.println(s.getTitle()));


        albumDao.addSongIn(1L, song);
        songsIn = albumDao.getSongsIn(1L);
        songsIn.stream().forEach(s -> System.out.println(s.getTitle()));
    }
}

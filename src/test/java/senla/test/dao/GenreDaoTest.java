package senla.test.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.test.configuration.Application;
import senla.dao.GenreDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {Application.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
public class GenreDaoTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    public void testGenreDao(){
        String[] programGenres = new String[]{
            "POP", "RAP", "BLUES", "CHANSON", "CLASSICAL", "DISCO", "ELECTRONIC", "JAZZ", "MUSICAL", "RENAISSANCE",
                "ROCK", "SPIRITUAL"
        };
        String daoGenres[] = new String[programGenres.length];
        for(int i = 0; i < daoGenres.length; i++){
            daoGenres[i] = String.valueOf(genreDao.findById(i+1L).getGenreTitle());
        }
        Assert.assertArrayEquals(programGenres, daoGenres);
    }

    @Test
    public void findByTitleTest(){
        String searchParam = "POP";
        String genreTitle = String.valueOf(genreDao.findByTitle(searchParam).getGenreTitle());

        Assert.assertEquals(genreTitle, searchParam);

    }
}
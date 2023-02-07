package daoTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.configuration.DaoConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { DaoConfiguration.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class AccountDaoTest {
    @Test
    public void sayHello(){
        System.out.println("hello");
    }
}

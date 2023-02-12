package senla.daoTests;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import senla.configuration.Application;
import senla.dao.RoleDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {Application.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class RoleDaoTest {
    @Autowired
    RoleDao roleDao;

    @Test
    public void testRoleDao(){
        String[] programRoles = new String[]{
                "OWNER", "ADMINISTRATOR", "USER"
        };
        String daoRoles[] = new String[programRoles.length];
        for(int i = 0; i < daoRoles.length; i++){
            daoRoles[i] = String.valueOf(roleDao.findById(i+1L).getRoleTitle());
        }
        Assert.assertArrayEquals(programRoles, daoRoles);
    }
}

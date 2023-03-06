package senla.test.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import senla.configuration.WebMvcConfig;
import senla.dao.RoleDao;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration()
@Transactional
public class RoleDaoTest {
    @Autowired
    private RoleDao roleDao;

    @Test
    public void testRoleDao() {
        String[] programRoles = new String[]{
                "OWNER", "ADMINISTRATOR", "USER"
        };
        String daoRoles[] = new String[programRoles.length];
        for (int i = 0; i < daoRoles.length; i++) {
            daoRoles[i] = String.valueOf(roleDao.findById(i + 1L).getRoleTitle());
        }

        assertArrayEquals(programRoles, daoRoles);
    }
}

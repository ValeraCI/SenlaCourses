package senla.dao;

import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.models.Role;

@Repository
public class RoleDao extends AbstractDao<Role, Long> {
    public RoleDao() {
        super(Role.class);
    }
}

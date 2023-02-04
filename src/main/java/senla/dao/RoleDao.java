package senla.dao;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.models.Role;

@Repository
public class RoleDao extends AbstractDao<Role, Long> {
    public RoleDao(EntityManager entityManager) {
        super(Role.class, entityManager);
    }
}

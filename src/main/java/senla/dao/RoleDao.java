package senla.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.models.Role;

@Repository
public class RoleDao extends AbstractDao<Role, Long> {
    public RoleDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(Role.class, entityManager, criteriaBuilder);
    }
}

package senla.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.models.Role;

@Repository
public class RoleDao extends AbstractDao<Role, Long> {
    public RoleDao(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
        super(Role.class, entityManager, criteriaBuilder);
    }
}

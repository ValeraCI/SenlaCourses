package senla.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.criteria.*;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.Account;
import senla.models.Account_;
import senla.models.LoginDetails_;

@Repository
public class AccountDao extends AbstractDao<Account, Long> {

    public AccountDao(EntityManager entityManager) {
        super(Account.class, entityManager);
    }

    @Override
    public Long save(Account entity) {
        try {
            EntityTransaction ent = entityManager.getTransaction();
            ent.begin();
            entityManager.persist(entity);
            entityManager.persist(entity.getLoginDetails());
            ent.commit();
        }
        catch (Exception e){
            throw new DataBaseWorkException(e);
        }

        return 0l;
    }

    public Account findByEmail(String email){
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Account> query = builder.createQuery(typeParameterClass);

            Root<Account> root = query.from(typeParameterClass);
            root.fetch(Account_.LOGIN_DETAILS, JoinType.INNER);
            root.fetch(Account_.ROLE, JoinType.INNER);
            query
                    .select(root)
                    .where(builder
                            .equal(root.get(LoginDetails_.EMAIL), email)
                    );

            return entityManager.createQuery(query).getSingleResult();
        }catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }


}

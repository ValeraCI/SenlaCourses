package senla.dao;

import org.springframework.stereotype.Repository;
import senla.dao.abstractDao.AbstractDao;
import senla.exceptions.DataBaseWorkException;
import senla.models.AEntity_;
import senla.models.Account;
import senla.models.Account_;
import senla.models.LoginDetails;
import senla.models.LoginDetails_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

@Repository
public class AccountDao extends AbstractDao<Account, Long> {

    public AccountDao() {
        super(Account.class);
    }

    public Account findByEmail(String email) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Account> query = criteriaBuilder.createQuery(typeParameterClass);

            Root<Account> root = query.from(typeParameterClass);
            Join<Account, LoginDetails> join = root.join(Account_.LOGIN_DETAILS);
            root.fetch(Account_.ROLE, JoinType.INNER);
            query
                    .select(root)
                    .where(criteriaBuilder
                            .equal(join.get(LoginDetails_.EMAIL), email)
                    );

            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            throw new DataBaseWorkException(e);
        }
    }

    @Override
    public void update(Account entity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaUpdate<Account> criteriaAccountUpdate =
                    criteriaBuilder.createCriteriaUpdate(typeParameterClass);
            Root<Account> rootAccount = criteriaAccountUpdate.from(typeParameterClass);

            criteriaAccountUpdate
                    .set(rootAccount.get(Account_.NICKNAME), entity.getNickname())
                    .set(rootAccount.get(Account_.ROLE), entity.getRole())
                    .where(criteriaBuilder.equal(rootAccount.get(AEntity_.ID), entity.getId()));

            entityManager.createQuery(criteriaAccountUpdate).executeUpdate();
        } catch (Exception e) {
            throw new DataBaseWorkException(e);
        }
    }

    public Account findWithSavedAlbums(Long id) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            CriteriaQuery<Account> query = criteriaBuilder.createQuery(typeParameterClass);

            Root<Account> root = query.from(typeParameterClass);
            root.fetch(Account_.ROLE, JoinType.INNER);
            root.fetch(Account_.SAVED_ALBUMS, JoinType.INNER);
            query
                    .select(root)
                    .where(criteriaBuilder
                            .equal(root.get(Account_.ID), id)
                    );

            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            throw new DataBaseWorkException(e);
        }
    }
}

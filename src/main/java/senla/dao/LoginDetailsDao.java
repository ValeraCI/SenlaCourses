package senla.dao;

import org.springframework.stereotype.Repository;
import senla.exceptions.DataBaseWorkException;
import senla.models.LoginDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class LoginDetailsDao {

    @PersistenceContext
    protected EntityManager entityManager;

    public void update(LoginDetails loginDetails) {
        try {
            Query query = entityManager.createQuery(
                    "update LoginDetails ld " +
                            "set ld.password = :passwordParam " +
                            "where ld.account = :accountParam");

            query.setParameter("passwordParam", loginDetails.getPassword());
            query.setParameter("accountParam", loginDetails.getAccount());

            query.executeUpdate();
        } catch (Exception e) {
            throw new DataBaseWorkException(e);
        }
    }
}

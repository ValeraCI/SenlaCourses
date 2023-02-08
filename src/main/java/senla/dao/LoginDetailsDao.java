package senla.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import senla.exceptions.DataBaseWorkException;
import senla.models.*;

@Repository
@AllArgsConstructor
public class LoginDetailsDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public void update(LoginDetails loginDetails) {
        try {
            Query query = entityManager.createQuery(
                    "update LoginDetails ld " +
                            "set ld.password = :passwordParam " +
                            "where ld.account = :accountParam");

            query.setParameter("passwordParam", loginDetails.getPassword());
            query.setParameter("accountParam", loginDetails.getAccount());

            query.executeUpdate();
        } catch (Exception e){
            throw new DataBaseWorkException(e);
        }
    }
}

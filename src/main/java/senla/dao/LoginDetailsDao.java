package senla.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import senla.exceptions.ObjectNotFoundException;
import senla.models.LoginDetails;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LoginDetailsDao {
    private static final Logger logger = LoggerFactory.getLogger(LoginDetailsDao.class);

    private static final List<LoginDetails> loginDetails = new ArrayList<>();

    public LoginDetails getById(long id){
         Optional<LoginDetails> OLD = loginDetails.stream()
                .filter(a -> a.getAccountId() == id)
                .findFirst();
         if(OLD.isEmpty()){
             String errorMassage = "Аккаунт с индексом " + id + " не обнаружен";
             logger.error(errorMassage);
             throw new ObjectNotFoundException(errorMassage);
         }

        return OLD.get();
    }

    public void add(LoginDetails lDetails){
        loginDetails.add(lDetails);
    }

    public void updatePassword(long id, String password){
        LoginDetails lDetails = getById(id);
        lDetails.setPassword(password);
    }

    public void deleteById(long id){
        LoginDetails lDetails = getById(id);
        loginDetails.remove(lDetails);
    }
}

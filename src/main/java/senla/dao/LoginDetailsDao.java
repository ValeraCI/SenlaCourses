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

    public Optional<LoginDetails> getById(long id){
         return loginDetails.stream()
                .filter(a -> a.getAccount().getId() == id)
                .findFirst();
    }

    private LoginDetails getLoginDetailsById(long id){
        Optional<LoginDetails> accountOptional = getById(id);
        if(accountOptional.isEmpty()){
            String errorMassage = "Аккаунт с индексом " + id + " не обнаружен";
            logger.error(errorMassage);
            throw new ObjectNotFoundException(errorMassage);
        }
        return accountOptional.get();
    }

    public void add(LoginDetails lDetails){
        loginDetails.add(lDetails);
    }

    public void updatePassword(long id, String password){
        LoginDetails lDetails = getLoginDetailsById(id);
        lDetails.setPassword(password);
    }

    public void delete(long id){
        LoginDetails lDetails = getLoginDetailsById(id);
        loginDetails.remove(lDetails);
    }

    public Optional<LoginDetails> getByEmail(String email){
        return loginDetails.stream()
                .filter(a -> a.getEmail().equals(email))
                .findFirst();
    }
}

package senla.test.util;

import senla.models.Account;
import senla.models.LoginDetails;
import senla.models.Role;
import senla.models.RoleTitle;

import java.util.HashSet;

public class ObjectCreator {
    public static Account createAccount(){
        Account account = new Account();
        account.setLoginDetails(new LoginDetails(account,"test@mail.ru", "1234"));
        account.setId(1L);
        account.setRole(new Role(3L, RoleTitle.USER));
        account.setSavedAlbums(new HashSet<>());

        return account;
    }
}

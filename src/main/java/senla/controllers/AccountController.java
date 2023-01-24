package senla.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import senla.dto.AccountDto;
import senla.dto.LoginDetailsDto;
import senla.services.AccountService;
import senla.util.Json;


@Controller
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    public void add(String jsonAccountDto, String jsonLoginDetailsDto){
        AccountDto accountDto = Json.deserialize(jsonAccountDto, AccountDto.class);
        LoginDetailsDto loginDetailsDto = Json.deserialize(jsonLoginDetailsDto, LoginDetailsDto.class);
        accountService.add(accountDto, loginDetailsDto);
        logger.info("Добавление аккаунта и данных для входа c индексами {}, {} произошло успешно",
                accountDto.getId(), loginDetailsDto.getAccountId());
    }

    public void remove(long id){
        accountService.deleteById(id);
    }

    public void updateData(String jsonAccountDto){
        AccountDto accountDto = Json.deserialize(jsonAccountDto, AccountDto.class);
        accountService.updateData(accountDto);
        logger.info("Обновление аккаунта с индексом {} произошло успешно", accountDto.getId());
    }

    public void updatePasswordById(long id, String password){
        accountService.updatePassword(id, password);
        logger.info("Обновление пароля для аккаунта с индексом {} произошло успешно", id);
    }

    public String getById(long id){
        return Json.serialize(accountService.getById(id));
    }

    public String getLoginDetailsById(long id){
        return Json.serialize(accountService.getLoginDetailsById(id));
    }

    public void addSavedAlbum(long id, long albumId){
        accountService.addSavedAlbum(id, albumId);
    }

    public void removeSavedAlbum(long id, long albumId){
        accountService.removeSavedAlbum(id, albumId);
    }
}

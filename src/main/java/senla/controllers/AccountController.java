package senla.controllers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import senla.dto.AccountDto;
import senla.dto.LoginDetailsDto;
import senla.services.AccountService;
import senla.util.Json;


@Controller
@RequiredArgsConstructor
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    @NonNull
    private final AccountService accountService;
    @NonNull
    private final Json json;



    public void add(String jsonAccountDto, String jsonLoginDetailsDto){
        AccountDto accountDto = json.deserialize(jsonAccountDto, AccountDto.class);
        LoginDetailsDto loginDetailsDto = json.deserialize(jsonLoginDetailsDto, LoginDetailsDto.class);
        accountService.add(accountDto, loginDetailsDto);
        logger.info("Добавление аккаунта и данных для входа c индексам {} произошло успешно",
                accountDto.getId());
    }

    public void remove(long id){
        accountService.deleteById(id);
    }

    public void updateData(String jsonAccountDto){
        AccountDto accountDto = json.deserialize(jsonAccountDto, AccountDto.class);
        accountService.updateData(accountDto);
        logger.info("Обновление аккаунта с индексом {} произошло успешно", accountDto.getId());
    }

    public void updatePasswordById(long id, String password){
        accountService.updatePassword(id, password);
        logger.info("Обновление пароля для аккаунта с индексом {} произошло успешно", id);
    }

    public String getById(long id){
        return json.serialize(accountService.getAccountDtoById(id));
    }

    public String getLoginDetailsDtoById(long id){
        return json.serialize(accountService.getLoginDetailsDtoById(id));
    }

    public void addSavedAlbum(long id, long albumId){
        accountService.addSavedAlbum(id, albumId);
    }

    public void removeSavedAlbum(long id, long albumId){
        accountService.removeSavedAlbum(id, albumId);
    }
}

package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.annotations.Loggable;
import senla.dto.CreateAccountDataDto;
import senla.dto.UpdateAccountDto;
import senla.services.AccountService;
import senla.util.Json;


@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final Json json;

    @Loggable
    public String getById(long id){
        return json.serialize(accountService.getAccountDtoById(id));
    }

    @Loggable
    public String getByEmail(String email) {
        return json.serialize(accountService.getAccountWithLoginDetailsDtoByEmail(email));
    }

    @Loggable
    public void add(String jsonCreateAccountDataDto){
        CreateAccountDataDto createAccountDataDto = json.deserialize(jsonCreateAccountDataDto,
                CreateAccountDataDto.class);

        accountService.add(createAccountDataDto);
    }

    @Loggable
    public void remove(long id){
        accountService.deleteById(id);
    }

    @Loggable
    public void updateData(String jsonUpdateAccountDto){
        UpdateAccountDto accountDto = json.deserialize(jsonUpdateAccountDto, UpdateAccountDto.class);
        accountService.updateData(accountDto);

    }

    @Loggable
    public void updatePasswordById(long id, String password){
        accountService.updatePassword(id, password);
    }

    @Loggable
    public void deleteById(long id){
        accountService.deleteById(id);
    }
}

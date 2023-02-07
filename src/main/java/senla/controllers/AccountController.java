package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import senla.annotations.Loggable;
import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountDto;
import senla.dto.album.AlbumInfoDto;
import senla.services.AccountService;
import senla.util.Json;

import java.util.Set;


@Controller
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final Json json;

    @Loggable
    public String getById(Long id){
        return json.serialize(accountService.getAccountDtoById(id));
    }

    @Loggable
    public String getByEmail(String email) {
        return json.serialize(accountService.getAccountWithLoginDetailsDtoByEmail(email));
    }

    @Loggable
    public void save(String jsonCreateAccountDataDto){
        AccountDataDto createAccountDataDto = json.deserialize(jsonCreateAccountDataDto,
                AccountDataDto.class);

        accountService.save(createAccountDataDto);
    }

    @Loggable
    public void removeById(Long id){
        accountService.deleteById(id);
    }

    @Loggable
    public void updateData(String jsonAccountDto){
        AccountDto accountDto = json.deserialize(jsonAccountDto, AccountDto.class);
        accountService.updateData(accountDto);

    }

    @Loggable
    public Set<AlbumInfoDto> getSavedAlbumsById(Long id){ //TODO возвращать json
        return accountService.getSavedAlbumsById(id);
    }

    @Loggable
    public void addSavedAlbum(Long accountId, Long albumId){
        accountService.addSavedAlbum(accountId, albumId);
    }

    @Loggable
    public void removeSavedAlbum(Long accountId, Long albumId){
        accountService.removeSavedAlbum(accountId, albumId);
    }
}

package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import senla.annotations.Loggable;
import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.UpdateAccountDto;
import senla.services.api.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<AccountMainDataDto> findAll() {
        return accountService.findAllAccountMainDataDto();
    }


    @Loggable
    @GetMapping("/{id}")
    public AccountMainDataDto findById(@PathVariable("id") Long id){
        return accountService.findAccountMainDataDtoById(id);
    }

    @Loggable
    @PostMapping()
    public Long save(@RequestBody AccountDataDto accountDataDto){
        return accountService.save(accountDataDto);
    }

    @Loggable
    @DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") Long id){
        accountService.deleteById(id);
    }

    @Loggable
    @PatchMapping("/{id}")
    public void updateData(@PathVariable("id") Long id, @RequestBody UpdateAccountDto accountUpdateDto){
        accountService.updateData(id, accountUpdateDto);
    }

    @Loggable
    @PostMapping("/{accountId}/albums/{albumId}")
    public void addSavedAlbum(@PathVariable("accountId") Long accountId, @PathVariable("albumId") Long albumId){
        accountService.addSavedAlbum(accountId, albumId);
    }

    @Loggable
    @DeleteMapping("/{accountId}/albums/{albumId}")
    public void removeSavedAlbum(@PathVariable("accountId") Long accountId, @PathVariable("albumId") Long albumId){
        accountService.removeSavedAlbum(accountId, albumId);
    }
}

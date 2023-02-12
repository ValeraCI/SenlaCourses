package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import senla.annotations.Loggable;
import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.UpdateAccountDto;
import senla.services.AccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Loggable
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
    public void save(@RequestBody AccountDataDto accountDataDto){
        accountService.save(accountDataDto);
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
    @PostMapping("/{accountId}/{albumId}")
    public void addSavedAlbum(@PathVariable("accountId") Long accountId, @PathVariable("albumId") Long albumId){
        accountService.addSavedAlbum(accountId, albumId);
    }

    @Loggable
    @DeleteMapping("/{accountId}/{albumId}")
    public void removeSavedAlbum(@PathVariable("accountId") Long accountId, @PathVariable("albumId") Long albumId){
        accountService.removeSavedAlbum(accountId, albumId);
    }


}

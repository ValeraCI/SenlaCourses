package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import senla.annotations.Loggable;
import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.UpdateAccountDto;
import senla.services.api.AccountService;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public List<AccountMainDataDto> findAll() {
        return accountService.findAllAccountMainDataDto();
    }


    @Loggable
    @GetMapping("/{id}")
    public AccountMainDataDto findById(@PathVariable("id") Long id) {
        return accountService.findAccountMainDataDtoById(id);
    }

    @Loggable
    @PostMapping()
    public Long save(@RequestBody AccountDataDto accountDataDto) {
        return accountService.save(accountDataDto);
    }

    @Loggable
    @DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") Long id) {
        accountService.deleteById(id);
    }

    @Loggable
    @PatchMapping("/{id}")
    public void updateData(@PathVariable("id") Long id, @RequestBody UpdateAccountDto accountUpdateDto) {
        accountService.updateData(id, accountUpdateDto);
    }

    @Loggable
    @PostMapping("/{accountId}/albums/{albumId}")
    public void addSavedAlbum(@PathVariable("accountId") Long accountId, @PathVariable("albumId") Long albumId) {
        accountService.addSavedAlbum(accountId, albumId);
    }

    @Loggable
    @DeleteMapping("/{accountId}/albums/{albumId}")
    public void removeSavedAlbum(@PathVariable("accountId") Long accountId, @PathVariable("albumId") Long albumId) {
        accountService.removeSavedAlbum(accountId, albumId);
    }
}

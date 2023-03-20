package senla.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import senla.annotations.Loggable;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.RegistrationRequest;
import senla.dto.account.UpdateAccountDataDto;
import senla.dto.account.UpdateAccountRoleDto;
import senla.models.AccountDetails;
import senla.services.api.AccountService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public List<AccountMainDataDto> findAll(
            @RequestParam(name = "pageNumber", defaultValue = "1") String pageNumber,
            @RequestParam(name = "limit", defaultValue = "10") String limit) {

        return accountService.findAllAccountMainDataDto(pageNumber, limit);
    }


    @Loggable
    @GetMapping("/{id}")
    public AccountMainDataDto findById(@PathVariable("id") Long id) {
        return accountService.findAccountMainDataDtoById(id);
    }

    @Loggable
    @PostMapping("/register")
    public Long save(@Valid @RequestBody RegistrationRequest loginRequest) {
        return accountService.save(loginRequest);
    }

    @Loggable
    @DeleteMapping("/{id}")
    public void removeById(@PathVariable("id") Long id,
                           @AuthenticationPrincipal AccountDetails accountDetails) {

        accountService.deleteById(id, accountDetails);
    }

    @Loggable
    @PatchMapping("/{id}")
    public void updateData(@PathVariable("id") Long id,
                           @Valid @RequestBody UpdateAccountDataDto accountUpdateDto,
                           @AuthenticationPrincipal AccountDetails accountDetails) {

        accountService.updateData(id, accountUpdateDto, accountDetails);
    }

    @Loggable
    @PostMapping("/{accountId}/albums/{albumId}")
    public void addSavedAlbum(@PathVariable("accountId") Long accountId,
                              @PathVariable("albumId") Long albumId,
                              @AuthenticationPrincipal AccountDetails accountDetails) {

        accountService.addSavedAlbum(accountId, albumId, accountDetails);
    }

    @Loggable
    @DeleteMapping("/{accountId}/albums/{albumId}")
    public void removeSavedAlbum(@PathVariable("accountId") Long accountId,
                                 @PathVariable("albumId") Long albumId,
                                 @AuthenticationPrincipal AccountDetails accountDetails) {

        accountService.removeSavedAlbum(accountId, albumId, accountDetails);
    }

    @Loggable
    @PatchMapping("/role/{id}")
    public void updateRole(@PathVariable("id") Long id,
                           @Valid @RequestBody UpdateAccountRoleDto updateAccountRoleDto) {

        accountService.updateRole(id, updateAccountRoleDto);
    }
}

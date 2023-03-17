package senla.services.api;

import senla.dto.account.AccountMainDataDto;
import senla.dto.account.RegistrationRequest;
import senla.dto.account.UpdateAccountDataDto;
import senla.dto.account.UpdateAccountRoleDto;
import senla.models.AccountDetails;

import java.util.List;

public interface AccountService {

    Long save(RegistrationRequest accountDataDto);

    AccountMainDataDto findAccountMainDataDtoById(Long id);

    List<AccountMainDataDto> findAllAccountMainDataDto(Long firstResult, Integer limit);

    void updateData(Long id, UpdateAccountDataDto accountUpdateDto, AccountDetails accountDetails);

    void updateRole(Long id, UpdateAccountRoleDto accountUpdateDto);

    void deleteById(Long id, AccountDetails accountDetails);

    void addSavedAlbum(Long accountId, Long albumId, AccountDetails accountDetails);

    void removeSavedAlbum(Long accountId, Long albumId, AccountDetails accountDetails);
}

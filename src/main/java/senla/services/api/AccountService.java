package senla.services.api;

import senla.dto.RegistrationRequest;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.UpdateAccountDataDto;
import senla.dto.account.UpdateAccountRoleDto;

import java.util.List;

public interface AccountService {

    Long save(RegistrationRequest accountDataDto);

    AccountMainDataDto findAccountMainDataDtoById(Long id);

    List<AccountMainDataDto> findAllAccountMainDataDto();

    void updateData(Long id, UpdateAccountDataDto accountUpdateDto);

    void updateRole(Long id, UpdateAccountRoleDto accountUpdateDto);

    void deleteById(Long id);

    void addSavedAlbum(Long accountId, Long albumId);

    void removeSavedAlbum(Long accountId, Long albumId);
}

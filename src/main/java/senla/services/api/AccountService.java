package senla.services.api;

import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.AccountWithLoginDetailsDto;
import senla.dto.account.UpdateAccountDto;

import java.util.List;

public interface AccountService {

    Long save(AccountDataDto accountDataDto);

    AccountMainDataDto findAccountMainDataDtoById(Long id);

    List<AccountMainDataDto> findAllAccountMainDataDto();

    AccountWithLoginDetailsDto findAccountWithLoginDetailsDtoByEmail(String email);

    void updateData(Long id, UpdateAccountDto accountUpdateDto);

    void deleteById(Long id);

    void addSavedAlbum(Long accountId, Long albumId);

    void removeSavedAlbum(Long accountId, Long albumId);
}

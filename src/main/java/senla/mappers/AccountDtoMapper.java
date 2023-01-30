package senla.mappers;

import org.springframework.stereotype.Component;
import senla.dto.AccountDto;
import senla.dto.AccountWithLoginDetailsDto;
import senla.exceptions.MultipleObjectsFoundException;
import senla.models.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDtoMapper implements Mapper{
    @Override
    public List<AccountDto> createObjects(ResultSet resultSet) {
        List<AccountDto> accountDtoList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nickname = resultSet.getString("nickname");
                Role role = Role.valueOf(resultSet.getString("title").toUpperCase());
                AccountDto accountDto = new AccountDto(id, nickname, role);
                accountDtoList.add(accountDto);
            }
        }catch (SQLException e) {
            new RuntimeException(e);
        }

        return accountDtoList;
    }

    @Override
    public AccountDto createObject(ResultSet resultSet) {
        List<AccountDto> accountDtoLong = createObjects(resultSet);
        if(accountDtoLong.size() != 1) {
            throw new MultipleObjectsFoundException("MultipleObjectsFoundException");
        }

        return accountDtoLong.get(0);
    }
}

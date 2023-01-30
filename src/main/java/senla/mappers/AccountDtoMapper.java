package senla.mappers;

import org.springframework.stereotype.Component;
import senla.dto.AccountDto;
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
        try {
            List<AccountDto> accountDtoList = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nickname = resultSet.getString("nickname");
                Role role = Role.valueOf(resultSet.getString("title").toUpperCase());
                AccountDto accountDto = new AccountDto(id, nickname, role);
                accountDtoList.add(accountDto);
            }
            return accountDtoList;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

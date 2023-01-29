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
        List<AccountDto> accountDtoList = new ArrayList<>();

        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                new RuntimeException(e);
            }

            long id = 0;
            String nickname = "";
            Role role = null;
            try {
                id = resultSet.getLong("id");
                nickname = resultSet.getString("nickname");
                role = Role.valueOf(resultSet.getString("title").toUpperCase());
            } catch (SQLException e) {
                new RuntimeException(e);
            }

            AccountDto accountDto = new AccountDto(id, nickname, role);
            accountDtoList.add(accountDto);
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

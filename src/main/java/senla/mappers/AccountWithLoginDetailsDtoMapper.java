package senla.mappers;

import org.springframework.stereotype.Component;
import senla.dto.AccountWithLoginDetailsDto;
import senla.exceptions.MultipleObjectsFoundException;
import senla.models.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountWithLoginDetailsDtoMapper implements Mapper{
    @Override
    public List<AccountWithLoginDetailsDto> createObjects(ResultSet resultSet) {
        List<AccountWithLoginDetailsDto> accountWithLoginDetailsDtoList = new ArrayList<>();

        try {
            while (resultSet.next()) {
                try {
                    long id = resultSet.getLong("id");
                    String nickname = resultSet.getString("nickname");
                    Role role = Role.valueOf(resultSet.getString("title").toUpperCase());
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    AccountWithLoginDetailsDto accountDto = new AccountWithLoginDetailsDto(id, nickname, role, email, password);
                    accountWithLoginDetailsDtoList.add(accountDto);
                } catch (SQLException e) {
                    new RuntimeException(e);
                }
            }
        }catch (SQLException e) {
            new RuntimeException(e);
        }

        return accountWithLoginDetailsDtoList;
    }

    @Override
    public AccountWithLoginDetailsDto createObject(ResultSet resultSet) {
        List<AccountWithLoginDetailsDto> accountWithLoginDetailsDtoList = createObjects(resultSet);
        if(accountWithLoginDetailsDtoList.size() != 1) {
            throw new MultipleObjectsFoundException("MultipleObjectsFoundException");
        }

        return accountWithLoginDetailsDtoList.get(0);
    }
}

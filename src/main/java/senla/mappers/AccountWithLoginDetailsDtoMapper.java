package senla.mappers;

import senla.dto.AccountWithLoginDetailsDto;
import senla.exceptions.MultipleObjectsFoundException;
import senla.models.Role;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountWithLoginDetailsDtoMapper implements Mapper{
    @Override
    public List<AccountWithLoginDetailsDto> createObjects(ResultSet resultSet) {
        List<AccountWithLoginDetailsDto> accountWithLoginDetailsDtoList = new ArrayList<>();

        while (true){
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                new RuntimeException(e);
            }

            long id = 0;
            String nickname = "";
            Role role = null;
            String email = "";
            String password = "";
            try {
                id = resultSet.getLong("id");
                nickname = resultSet.getString("nickname");
                role = Role.valueOf(resultSet.getString("title").toUpperCase());
                email = resultSet.getString("email");
                password = resultSet.getString("password");
            } catch (SQLException e) {
                new RuntimeException(e);
            }

            AccountWithLoginDetailsDto accountDto = new AccountWithLoginDetailsDto(id, nickname, role, email, password);
            accountWithLoginDetailsDtoList.add(accountDto);
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

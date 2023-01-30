package senla.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import senla.dto.*;
import senla.mappers.AccountDtoMapper;
import senla.mappers.AccountWithLoginDetailsDtoMapper;
import senla.util.ConnectionHolder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class AccountDao {
    private final ConnectionHolder connectionHolder;
    private final AccountDtoMapper accountDtoMapper;
    private final AccountWithLoginDetailsDtoMapper accountWithLoginDetailsDtoMapper;

    public AccountDto getAccountDtoById(long id){
        String threadName = Thread.currentThread().getName();
        String sqlRequest = """
                    select a.id, nickname, title
                           from accounts a join roles r on a.role_id = r.id
                           where a.id = %d;
                """.formatted(id);

        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            return accountDtoMapper.createObject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AccountWithLoginDetailsDto getAccountWithLoginDetailsDtoByEmail(String email){
        String threadName = Thread.currentThread().getName();
        String sqlRequest = """
                    select a.id, nickname, title, email, password
                           from accounts a
                                join roles r on a.role_id = r.id
                                join login_details ld on a.id = ld.account_id
                           where email = '%s';
                """.formatted(email);

        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlRequest);
            return accountWithLoginDetailsDtoMapper.createObject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public long addAccount(CreateAccountDto accountDto){
        String threadName = Thread.currentThread().getName();
        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            String sqlRequest = """
                        insert into accounts(nickname, registration_date, role_id)
                               values ('%s', '%s', 3);
                    """.formatted(accountDto.getNickname(), accountDto.getRegistrationDate(), 3);

            statement.execute(sqlRequest);

            ResultSet resultSet = statement.executeQuery("SELECT currval('account_id_seq');");
            resultSet.next();
            return resultSet.getLong("currval");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addLoginDetails(CreateLoginDetailsDto loginDetailsDto){
        String threadName = Thread.currentThread().getName();
        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            String sqlRequest = """
                insert into login_details(account_id, email, password) values
                            ( %d, '%s','%s');
                """.formatted(loginDetailsDto.getAccountId(), loginDetailsDto.getEmail(), loginDetailsDto.getPassword());

            statement.execute(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(UpdateAccountDto accountDto){
        String threadName = Thread.currentThread().getName();
        String sqlRequest = """
                update accounts
                       set nickname = '%s',
                           role_id = %d
                       where id = %d;
                """.formatted(accountDto.getNickname(), accountDto.getRoleId(), accountDto.getId());

        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            statement.execute(sqlRequest);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id){
        String threadName = Thread.currentThread().getName();

        String sqlRequest = "delete from accounts where id = %d;"
                .formatted(id);

        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            statement.execute(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePasswordById(long id, String password){
        String threadName = Thread.currentThread().getName();
        String sqlRequest = """
                update login_details 
                       set password = '%s'
                       where account_id = %d;
                """.formatted(password, id);

        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            statement.execute(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

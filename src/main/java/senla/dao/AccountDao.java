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

    public AccountDto getAccountDtoById(long id){
        String threadName = Thread.currentThread().getName();
        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select a.id, nickname, title" +
                            "    from accounts a join roles r on a.role_id = r.id" +
                            "    where a.id = " + id);
            return new AccountDtoMapper().createObject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AccountWithLoginDetailsDto getAccountWithLoginDetailsDtoByEmail(String email){
        String threadName = Thread.currentThread().getName();
        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "select a.id, nickname, title, email, password" +
                            "    from accounts a" +
                            "        join roles r on a.role_id = r.id" +
                            "        join login_details ld on a.id = ld.account_id" +
                            "    where email = '" + email + "'");
            return new AccountWithLoginDetailsDtoMapper().createObject(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public long addAccount(CreateAccountDto accountDto){
        String threadName = Thread.currentThread().getName();
        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            statement.execute(
                    "insert into accounts(nickname, registration_date, role_id) " +
                            "values ('" + accountDto.getNickname() + "', '" +
                             accountDto.getRegistrationDate() + "', 3);");

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
            statement.execute(
                    "insert into login_details(account_id, email, password) values " +
                            "(" + loginDetailsDto.getAccountId() + "," +
                            " '" + loginDetailsDto.getEmail() + "'," +
                            " '" + loginDetailsDto.getPassword() + "');");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(UpdateAccountDto accountDto){
        String threadName = Thread.currentThread().getName();
        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            statement.execute(
                    "update accounts" +
                            "    set nickname = '" + accountDto.getNickname() + "'," +
                            "        role_id = " + accountDto.getRoleId() + " " +
                            "    where id = " + accountDto.getId()  + ";");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(long id){
        String threadName = Thread.currentThread().getName();
        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            statement.execute(
                    "delete from accounts where id = " + id + ";");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePasswordById(long id, String password){
        String threadName = Thread.currentThread().getName();
        try(Statement statement = connectionHolder.getConnection(threadName).createStatement()) {
            statement.execute(
                    "update login_details " +
                            "set password = '" + password + "'" +
                            "where account_id = " + id + ";");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

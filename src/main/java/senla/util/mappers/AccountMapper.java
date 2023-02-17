package senla.util.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senla.dto.account.*;
import senla.models.*;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Objects;

@Component
public class AccountMapper {

    @Autowired
    private ModelMapper mapper;

   @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(AccountDataDto.class, Account.class)
                .addMappings(m -> m.skip(Account::setLoginDetails))
                .addMappings(m -> m.skip(Account::setRegistrationDate))
                .addMappings(m -> m.skip(Account::setRole))
                .setPostConverter(
                        accountDataDtoToAccountConverter());

        mapper.createTypeMap(UpdateAccountDto.class, Account.class)
                .addMappings(m -> m.skip(Account::setRole))
                .setPostConverter(
                        accountToUpdateAccountDtoConverter());

       mapper.createTypeMap(Account.class, AccountWithLoginDetailsDto.class)
               .addMappings(m -> m.skip(AccountWithLoginDetailsDto::setRole))
               .setPostConverter(
                       accountToAccountWithLoginDetailsDtoConverter());

    }

    public Converter<Account, AccountWithLoginDetailsDto> accountToAccountWithLoginDetailsDtoConverter() {
        return context -> {
            Account source = context.getSource();
            AccountWithLoginDetailsDto destination = context.getDestination();
            mapAccountWithLoginDetailsDtoSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<UpdateAccountDto, Account> accountToUpdateAccountDtoConverter() {
        return context -> {
            UpdateAccountDto source = context.getSource();
            Account destination = context.getDestination();
            mapAccountSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public Converter<AccountDataDto, Account> accountDataDtoToAccountConverter() {
        return context -> {
            AccountDataDto source = context.getSource();
            Account destination = context.getDestination();
            mapAccountDataDtoSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    public void mapAccountWithLoginDetailsDtoSpecificFields(Account source, AccountWithLoginDetailsDto destination) {
        destination.setRole(source.getRole().getRoleTitle());
    }

    public void mapAccountSpecificFields(UpdateAccountDto source, Account destination) {
        destination.setRole(new Role(source.getRoleId()));
    }

    public void mapAccountDataDtoSpecificFields(AccountDataDto source, Account destination) {
        destination.setRegistrationDate(LocalDate.now());
        LoginDetails loginDetails =
                new LoginDetails(destination, source.getEmail(), source.getPassword());
        destination.setLoginDetails(loginDetails);
        destination.setRole(new Role(3L, RoleTitle.USER));
    }

    public Account toEntity(AccountDataDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Account.class);
    }

    public AccountMainDataDto toAccountMainDataDto(Account entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, AccountMainDataDto.class);
    }

    public AccountWithLoginDetailsDto toAccountWithLoginDetailsDto(Account entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, AccountWithLoginDetailsDto.class);
    }

    public Account toEntity(UpdateAccountDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Account.class);
    }
}
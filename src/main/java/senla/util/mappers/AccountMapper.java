package senla.util.mappers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import senla.dto.account.AccountDataDto;
import senla.dto.account.AccountMainDataDto;
import senla.dto.account.AccountWithLoginDetailsDto;
import senla.dto.account.UpdateAccountDto;
import senla.models.Account;
import senla.models.LoginDetails;
import senla.models.Role;
import senla.models.RoleTitle;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

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
               .addMappings(m -> m.skip(AccountWithLoginDetailsDto::setEmail))
               .addMappings(m -> m.skip(AccountWithLoginDetailsDto::setPassword))
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
        destination.setEmail(source.getLoginDetails().getEmail());
        destination.setPassword(source.getLoginDetails().getPassword());
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

    public List<AccountMainDataDto> toAccountMainDataDtoList(List<Account> accounts) {
       List<AccountMainDataDto> accountMainDataDtoList = accounts
               .stream()
               .map(account -> Objects.isNull(account) ? null : mapper.map(account, AccountMainDataDto.class))
               .collect(Collectors.toList());

       return accountMainDataDtoList;
    }
}
package ru.vcodetsev.account.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    final AccountRepository repository;
    final PasswordEncoder passwordEncoder;

    @Override
    public boolean isAccountExist(long id) {
        return repository.existsById(id);
    }

    @Override
    public AccountDto doctorInfo(long id) {
        Account account = account(id);
        if (!account.getRoles().contains(Role.doctor))
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account with ID %d isn't doctor"
            );
        return AccountDto.from(account);
    }

    @Override
    public Collection<AccountDto> getDoctorsInfo(String nameFilter, int from, int count) {
        List<Account> doctors = repository
                .findAll()
                .stream()
                .filter(account -> account.getRoles().contains(Role.doctor))
                .toList();
        return doctors
                .stream()
                .map(AccountDto::from)
                .collect(Collectors.toList())
                .subList(from, Math.min(from + count, doctors.size()));
    }

    @Override
    public AccountDto accountInfo(long id) {
        Account account = account(id);
        return AccountDto.from(account);
    }

    @Override
    public Account account(long id) {
        Optional<Account> accountOptional = repository.findById(id);
        if (accountOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account with id %d doesnt exist".formatted(id)
            );

        return accountOptional.get();
    }

    @Override
    public void createAccount(
            String firstName,
            String lastName,
            String username,
            String password,
            Collection<Role> roles
    ) {
        checkUsername(username);

        Account account = Account.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .firstName(firstName)
                .lastName(lastName)
                .roles(roles)
                .state(AccountState.normal)
                .build();
        repository.save(account);
    }

    @Override
    public void updateAccount(long id, AccountUpdateRequest request) {
        Account account = account(id);
        checkUsername(request.getUsername(), id);
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setUsername(request.getUsername());
        repository.save(account);
    }

    @Override
    public void updateAccountPassword(long id, AccountUpdatePasswordRequest request) {
        Account account = account(id);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        repository.save(account);
    }

    @Override
    public List<AccountDto> all(int from, int count) {
        return repository
                .findAll()
                .stream()
                .map(AccountDto::from)
                .collect(Collectors.toList())
                .subList(from, Math.min(from + count, repository.findAll().size()));
    }

    @Override
    public AccountDto updateAccountAdmin(
            long id,
            String firstName,
            String lastName,
            String username,
            String password,
            Collection<Role> roles
    ) {
        Account account = account(id);
        checkUsername(username, id);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setUsername(username);
        account.setRoles(roles);
        account.setPassword(passwordEncoder.encode(password));
        repository.save(account);
        return AccountDto.from(account);
    }

    @Override
    public void softDelete(long id) {
        account(id).setState(AccountState.deleted);
    }

    @Override
    public boolean isDoctorExist(long id) {
        if (repository.existsById(id)) {
            return repository.findById(id).get().getRoles().contains(Role.doctor);
        }

        return false;
    }

    public void checkUsername(String username, long id) {
        Optional<Account> accountOptional = repository.findByUsername(username);
        if (accountOptional.isPresent() && !accountOptional.get().getId().equals(id))
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Username '%s' is already in use".formatted(username)
            );
    }

    public void checkUsername(String username) {
        if (repository.findByUsername(username).isPresent())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Username '%s' is already in use".formatted(username)
            );
    }
}

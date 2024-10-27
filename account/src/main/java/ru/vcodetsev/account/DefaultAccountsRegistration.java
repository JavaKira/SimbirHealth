package ru.vcodetsev.account;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vcodetsev.account.account.AccountService;
import ru.vcodetsev.account.account.Role;
import ru.vcodetsev.account.account.admin.AccountCreateRequest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultAccountsRegistration {
    final AccountService accountService;
    List<AccountCreateRequest> createRequests = new ArrayList<>();

    @PostConstruct
    public void init() {
        createRequests.add(new AccountCreateRequest(
                "admin",
                "admin",
                "admin",
                "admin",
                List.of(Role.admin).toArray(new Role[0])
        ));
        createRequests.add(new AccountCreateRequest(
                "manager",
                "manager",
                "manager",
                "manager",
                List.of(Role.manager).toArray(new Role[0])
        ));
        createRequests.add(new AccountCreateRequest(
                "doctor",
                "doctor",
                "doctor",
                "doctor",
                List.of(Role.doctor).toArray(new Role[0])
        ));
        createRequests.add(new AccountCreateRequest(
                "user",
                "user",
                "user",
                "user",
                List.of(Role.user).toArray(new Role[0])
        ));


        createRequests.forEach(accountCreateRequest -> {
            try {
                accountService.createAccount(
                        accountCreateRequest.getFirstName(),
                        accountCreateRequest.getLastName(),
                        accountCreateRequest.getUsername(),
                        accountCreateRequest.getPassword(),
                        List.of(accountCreateRequest.getRoles())
                );
            } catch (Exception ignored) {
                //Если не получилось то фиг с ним
                log.error(ignored.getMessage(), ignored);
            }
        });
    }
}

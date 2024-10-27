package ru.vcodetsev.account.account.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vcodetsev.account.account.AccountDto;
import ru.vcodetsev.account.account.AccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Accounts/Admin") //todo вот так на всех модулях
class AdminAccountController {
    final AccountService accountService;

    @GetMapping
    @Operation(
            summary = "Получение списка всех аккаунтов",
            description = "Только администраторы"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    List<AccountDto> getAccounts(int from, int count) {
        return accountService.all(from, count);
    }

    @PostMapping
    @Operation(
            summary = "Создание администратором нового аккаунта",
            description = "Только администраторы"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    void createAccount(@RequestBody AccountCreateRequest request) {
        accountService.createAccount(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getPassword(),
                List.of(request.getRoles())
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение администратором аккаунта по id",
            description = "Только администраторы"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    AccountDto updateAccount(AccountUpdateAdminRequest request, @PathVariable long id) {
        return accountService.updateAccountAdmin(
                id,
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getPassword(),
                List.of(request.getRoles())
        );
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Мягкое удаление аккаунта по id",
            description = "Только администраторы"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    void deleteAccount(@PathVariable long id) {
        accountService.softDelete(id);
    }
}

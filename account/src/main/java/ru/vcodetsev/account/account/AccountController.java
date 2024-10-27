package ru.vcodetsev.account.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vcodetsev.account.jwt.JwtService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Accounts")
class AccountController {
    final AccountService accountService;
    final JwtService jwtService;

    @GetMapping("/Me")
    @Operation(
            summary = "Получение данных о текущем аккаунте",
            description = "Только авторизованные пользователи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    AccountDto me(HttpServletRequest httpRequest) {
        return accountService.accountInfo(jwtService.extractId(jwtService.token(httpRequest).orElseThrow()));
    }

    @GetMapping("/IsExist")
    @Operation(
            summary = "Проверка существования пользователя по id"
    )
    boolean isAccountExist(@RequestParam long id) {
        return accountService.isAccountExist(id);
    }

    @PatchMapping ("/Update")
    @Operation(
            summary = "Обновление своего аккаунта",
            description = "Только авторизованные пользователи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    AccountDto update(HttpServletRequest httpRequest, AccountUpdateRequest request) {
        long id = jwtService.extractId(jwtService.token(httpRequest).orElseThrow());
        accountService.updateAccount(id, request);
        return accountService.accountInfo(id);
    }

    @PatchMapping ("/UpdatePassword")
    @Operation(
            summary = "Обновление пароля своего аккаунта",
            description = "Только авторизованные пользователи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    void updatePassword(HttpServletRequest httpRequest, AccountUpdatePasswordRequest request) {
        long id = jwtService.extractId(jwtService.token(httpRequest).orElseThrow());
        accountService.updateAccountPassword(id, request);
    }
}

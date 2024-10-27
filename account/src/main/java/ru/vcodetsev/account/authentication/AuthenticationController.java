package ru.vcodetsev.account.authentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vcodetsev.account.CringeException;
import ru.vcodetsev.account.jwt.JwtService;

@RestController
@RequestMapping("/Authentication")
@RequiredArgsConstructor
class AuthenticationController {
    final AuthenticationService service;
    final JwtService jwtService;
    final TokenIntrospectionService tokenIntrospectionService;

    @Operation(summary = "Регистрация нового аккаунта")
    @PostMapping("/SingUp")
    void singUp(@RequestBody SignUpRequest request) {
        service.singUp(request);
    }

    @Operation(summary = "Получение нового jwt токена пользователя")
    @PostMapping("/SingIn")
    AuthenticationResponse singIn(@RequestBody SignInRequest request) {
        return service.singIn(request);
    }

    @Operation(summary = "Выход из аккаунта")
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/SignOut")
    void signOut(HttpServletRequest httpRequest) {
        service.signOut(jwtService.token(httpRequest).orElseThrow());
    }

    @Operation(summary = "Интроспекция токена")
    @GetMapping("/Validate")
    TokenIntrospectionResult validate(String accessToken) {
        return tokenIntrospectionService.introspect(accessToken);
    }

    @Operation(summary = "Обновление пары токенов")
    @PostMapping("/Refresh")
    AuthenticationResponse Refresh(@RequestBody RefreshRequest request) {
        return service.refresh(request.getRefreshToken());
    }
}

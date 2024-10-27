package ru.vcodetsev.account.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.vcodetsev.account.account.Account;
import ru.vcodetsev.account.account.AccountService;
import ru.vcodetsev.account.jwt.JwtService;

@Service
@RequiredArgsConstructor
public class TokenIntrospectionService {
    final JwtService jwtService;
    final AccountService accountService;

    public TokenIntrospectionResult introspect(String accessToken) {
        if (jwtService.isRefreshToken(accessToken))
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "For authorization required accessToken, given refresh token"
            );

        boolean isTokenExpired = jwtService.isTokenExpired(accessToken);
        if (!isTokenExpired) {
            Account account = accountService.account(jwtService.extractId(accessToken));
            boolean isTokenValid = jwtService.isTokenValid(
                    accessToken,
                    account
            );
            if (isTokenValid) {
                return new TokenIntrospectionResult(
                        true,
                        account
                                .getRoles()
                                .stream()
                                .map(role -> "ROLE_" + role.name().toUpperCase())
                                .toArray(String[]::new),
                        account.getId(),
                        account.getUsername(),
                        jwtService.extractExpiration(accessToken).getTime()
                );
            }
        }

        return new TokenIntrospectionResult(
                false
        );
    }
}

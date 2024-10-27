package ru.vcodetsev.account.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import ru.vcodetsev.account.account.Account;

import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface JwtService {
    String extractLogin(String token);

    long extractId(String token);

    Optional<String> token(HttpServletRequest request);

    void banToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(Account account);

    String generateRefreshToken(Account account);

    <T> T accessUser(HttpServletRequest request, Function<Long, T> userConsumer);

    void accessUserVoid(HttpServletRequest request, Consumer<Long> userConsumer);

    boolean isTokenExpired(String token);

    Date extractExpiration(String token);

    boolean isRefreshToken(String token);
}

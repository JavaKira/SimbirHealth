package ru.vcodetsev.account.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.vcodetsev.account.account.Account;
import ru.vcodetsev.account.account.AccountRepository;
import ru.vcodetsev.account.account.AccountService;
import ru.vcodetsev.account.account.Role;
import ru.vcodetsev.account.jwt.JwtService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @Override
    public void singUp(SignUpRequest request) {
        accountService.createAccount(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getPassword(),
                List.of(Role.user)
        );
    }

    @Override
    public AuthenticationResponse singIn(SignInRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Account account = accountRepository.findByUsername(request.getUsername())
                .orElseThrow();

        String accessToken = jwtService.generateToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);
        return new AuthenticationResponse(refreshToken, accessToken);
    }

    @Override
    public void signOut(String token) {
        jwtService.banToken(token);
    }

    @Override
    public AuthenticationResponse refresh(String refreshToken) {
        Account account = accountRepository.findByUsername(jwtService.extractLogin(refreshToken))
                .orElseThrow();

        String accessToken = jwtService.generateToken(account);
        return new AuthenticationResponse(refreshToken, accessToken);
    }
}

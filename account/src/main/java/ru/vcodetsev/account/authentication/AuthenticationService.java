package ru.vcodetsev.account.authentication;

public interface AuthenticationService {
    void singUp(SignUpRequest request);

    AuthenticationResponse singIn(SignInRequest request);

    void signOut(String token);

    AuthenticationResponse refresh(String refreshToken);
}

package ru.vcodetsev.account.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenIntrospectionResult {
    private boolean active;
    private String[] role;
    private long userId;
    private String username;
    private long expiresIn;

    public TokenIntrospectionResult(boolean active) {
        this.active = active;
    }
}

package ru.vcodetsev.timetable.jwt;

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
}

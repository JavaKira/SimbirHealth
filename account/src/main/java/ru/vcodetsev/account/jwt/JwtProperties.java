package ru.vcodetsev.account.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:jwt.properties")
public class JwtProperties {
    private String secret;
    private long accessExpiration;
    private long expiration;
}

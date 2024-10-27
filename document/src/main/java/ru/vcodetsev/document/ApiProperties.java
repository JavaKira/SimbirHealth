package ru.vcodetsev.document;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "api")
public class ApiProperties {
    private String accountServiceUrl;
    private String hospitalServiceUrl;
}

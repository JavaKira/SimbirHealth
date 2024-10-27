package ru.vcodetsev.document.account;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Override
    public boolean isAccountExist(long accountId) {
        return Boolean.TRUE.equals(WebClient
                .create("http://localhost:8081/api/Accounts/IsExist?id=" + accountId)
                .get()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .retrieve()
                .bodyToMono(Boolean.class).block());
    }

    @Override
    public boolean isDoctorExist(long doctorId) {
        return Boolean.TRUE.equals(WebClient
                .create("http://localhost:8081/api/Accounts/IsDoctorExist?id=" + doctorId)
                .get()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .retrieve()
                .bodyToMono(Boolean.class).block());
    }
}

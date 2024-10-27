package ru.vcodetsev.account.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String firstName, lastName, username;
    private Collection<Role> roles;

    public static AccountDto from(Account account) {
        return AccountDto
                .builder()
                .id(account.getId())
                .roles(account.getRoles())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .username(account.getUsername())
                .build();
    }
}

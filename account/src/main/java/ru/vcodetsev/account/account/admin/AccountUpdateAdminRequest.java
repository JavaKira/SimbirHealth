package ru.vcodetsev.account.account.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vcodetsev.account.account.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateAdminRequest {
    private String lastName, firstName, username, password;
    private Role[] roles;
}

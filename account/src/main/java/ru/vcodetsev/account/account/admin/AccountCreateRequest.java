package ru.vcodetsev.account.account.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vcodetsev.account.account.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateRequest {
    private String firstName;
    private String lastName;
    private String username; //имя пользователя
    private String password; //пароль
    private Role[] roles; // массив ролей пользователя
}

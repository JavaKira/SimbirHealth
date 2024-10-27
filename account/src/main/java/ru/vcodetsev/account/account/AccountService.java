package ru.vcodetsev.account.account;

import java.util.Collection;
import java.util.List;

public interface AccountService {
    boolean isAccountExist(long id);

    AccountDto doctorInfo(long id);

    Collection<AccountDto> getDoctorsInfo(String nameFilter, int from, int count);

    AccountDto accountInfo(long id);

    Account account(long id);

    void createAccount(
            String firstName,
            String lastName,
            String username,
            String password,
            Collection<Role> roles
    );

    void updateAccount(long id, AccountUpdateRequest request);

    void updateAccountPassword(long id, AccountUpdatePasswordRequest request);

    List<AccountDto> all(int from, int count);

    AccountDto updateAccountAdmin(long id, String firstName, String lastName, String username, String password, Collection<Role> roles);

    void softDelete(long id);

    boolean isDoctorExist(long id);
}

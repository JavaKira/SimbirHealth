package ru.vcodetsev.document.account;

public interface AccountService {
    boolean isAccountExist(long accountId);

    boolean isDoctorExist(long doctorId);
}

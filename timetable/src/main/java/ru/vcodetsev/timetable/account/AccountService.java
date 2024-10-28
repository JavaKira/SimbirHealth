package ru.vcodetsev.timetable.account;

public interface AccountService {
    boolean isAccountExist(long accountId);

    boolean isDoctorExist(long doctorId);
}

package ru.vcodetsev.document.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.vcodetsev.document.account.AccountService;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final AccountService accountService;

    public void checkAccountExist(long accountId) {
        if (!accountService.isAccountExist(accountId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account with id " + accountId + " does not exist"
            );
        }
    }

    //todo
    public void checkHospitalExist(long hospitalId) {

    }

    //todo
    public void checkDoctorExist(long doctorId) {
        if (!accountService.isAccountExist(doctorId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Account with id " + doctorId + " does not exist"
            );
        }
    }

    //todo
    public void checkRoomExist(String room) {

    }
}

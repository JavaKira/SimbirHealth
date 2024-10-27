package ru.vcodetsev.document.history;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.vcodetsev.document.validation.ValidationService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final VisitHistoryRepository visitHistoryRepository;
    private final ValidationService validationService;

    @Override
    public Collection<Long> getAllByAccount(long accountId) {
        // todo хотя хз, если пользователя нету в бд этого сервиса, то значит его нету, проверку можно сделать только при добавлении записи
        validationService.checkAccountExist(accountId);

        return visitHistoryRepository
                .findAll()
                .stream()
                .filter(visitHistory -> visitHistory.getPatientId() == accountId)
                .map(VisitHistory::getId)
                .toList();
    }

    @Override
    public VisitHistoryDto getInfoById(long historyId) {
        return VisitHistoryDto.from(visitHistory(historyId));
    }

    @Override
    public VisitHistory visitHistory(long historyId) {
        Optional<VisitHistory> accountOptional = visitHistoryRepository.findById(historyId);
        if (accountOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "VisitHistory with id %d doesnt exist".formatted(historyId)
            );

        return accountOptional.get();
    }

    @Override
    public VisitHistoryDto create(LocalDateTime date, long patientId, long hospitalId, long doctorId, String room, String data) {
        validationService.checkAccountExist(patientId);
        validationService.checkHospitalExist(hospitalId);
        validationService.checkDoctorExist(doctorId);
        validationService.checkRoomExist(room);

        VisitHistory visitHistory = VisitHistory
                .builder()
                .date(date)
                .patientId(patientId)
                .hospitalId(hospitalId)
                .doctorId(doctorId)
                .room(room)
                .data(data)
                .build();

        visitHistoryRepository.save(visitHistory);
        return VisitHistoryDto.from(visitHistory);
    }

    @Override
    public VisitHistoryDto update(long historyId, LocalDateTime date, long patientId, long hospitalId, long doctorId, String room, String data) {
        validationService.checkAccountExist(patientId);
        validationService.checkHospitalExist(hospitalId);
        validationService.checkDoctorExist(doctorId);
        validationService.checkRoomExist(room);

        //check visitHistory with id historyId existence
        VisitHistory visitHistory = visitHistory(historyId);
        VisitHistory newVisitHistory = VisitHistory
                .builder()
                .id(historyId)
                .date(date)
                .patientId(patientId)
                .hospitalId(hospitalId)
                .doctorId(doctorId)
                .room(room)
                .data(data)
                .build();

        visitHistoryRepository.save(newVisitHistory);
        return VisitHistoryDto.from(newVisitHistory);
    }
}

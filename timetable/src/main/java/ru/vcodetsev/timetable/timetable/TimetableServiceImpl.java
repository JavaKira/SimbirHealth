package ru.vcodetsev.timetable.timetable;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {
    final TimetableRepository timetableRepository;

    @Override
    public Timetable timetable(long timetableId) {
        Optional<Timetable> hospitalOptional = timetableRepository.findById(timetableId);
        if (hospitalOptional.isEmpty() || hospitalOptional.get().getState() == TimetableState.deleted)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Timetable with id %d doesnt exist".formatted(timetableId)
            );

        return hospitalOptional.get();
    }

    @Override
    public TimetableDto timetableDto(long timetableId) {
        return TimetableDto.from(timetable(timetableId));
    }

    @Override
    public TimetableDto createTimetable(long hospitalId, long doctorId, LocalDateTime from, LocalDateTime to, String room) {
        // нужно проверять не занята ли комната, не пересекаются ли расписания
        // не занят ли доктор, не пересекается ли,
        // кратно ли время 30 и 0 секунд, не больше ли 12 часов from -> to
        Timetable timetable = Timetable
                .builder()
                .hospitalId(hospitalId)
                .doctorId(doctorId)
                .from(from)
                .to(to)
                .room(room)
                .build();

        timetableRepository.save(timetable);
        return TimetableDto.from(timetable);
    }

    @Override
    public HospitalTimetableResponse createHospitalTimetable(long id) {
        //todo проверка существует ли hospital
        return new HospitalTimetableResponse(
                id,
                timetableRepository
                        .findAll()
                        .stream()
                        .filter(timetable -> timetable.getHospitalId() == id)
                        .toList()
        );
    }
}

package ru.vcodetsev.timetable.timetable;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.vcodetsev.timetable.appointment.AppointmentDto;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
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
    public TimetableDto updateTimetable(long id, long hospitalId, long doctorId, LocalDateTime from, LocalDateTime to, String room) {
        Timetable timetable = timetable(id);
        Timetable newTimetable = Timetable
                .builder()
                .id(id)
                .hospitalId(hospitalId)
                .doctorId(doctorId)
                .from(from)
                .to(to)
                .room(room)
                .build();

        timetableRepository.save(newTimetable);
        return TimetableDto.from(newTimetable);
    }

    @Override
    public void softDeleteTimetable(long id) {

    }

    @Override
    public HospitalTimetableResponse generateHospitalTimetable(long id) {
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

    @Override
    public void softDeleteDoctorTimetables(long doctorId) {

    }

    @Override
    public void softDeleteHospitalTimetables(long hospitalId) {

    }

    @Override
    public DoctorTimetableResponse generateDoctorTimetable(long doctorId) {
        return null;
    }

    @Override
    public RoomTimetableDto generateRoomTimetable(long hospitalId, String room) {
        return null;
    }

    @Override
    public Collection<LocalDateTime> freeTickets(long timetableId) {
        return List.of();
    }

    @Override
    public AppointmentDto createAppointment(long id, long hospitalId, long doctorId, LocalDateTime from, LocalDateTime to, String room) {
        return null;
    }

    @Override
    public void softDeleteAppointment(long appointmentId) {

    }

    @Override
    public Collection<AppointmentDto> getAccountAppointments(long id) {
        return List.of();
    }
}

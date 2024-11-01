package ru.vcodetsev.timetable.timetable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.vcodetsev.timetable.appointment.Appointment;
import ru.vcodetsev.timetable.appointment.AppointmentRepository;
import ru.vcodetsev.timetable.appointment.AppointmentState;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {
    final TimetableRepository timetableRepository;
    final AppointmentRepository appointmentRepository;

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
                .fromTime(from)
                .toTime(to)
                .room(room)
                .build();
        timetableRepository.save(timetable);

        Map<String, Appointment> appointments = new HashMap<>();
        long elements = Duration.between(from, to).toMinutes() / 30;
        for (int i = 0; i < elements; i++) {
            Appointment appointment = new Appointment();
            appointment.setTimetableId(timetable.getId());
            appointment.setState(AppointmentState.free);
            appointment.setTime(from.plusMinutes(30L * i));
            appointmentRepository.save(appointment);
            appointments.put(from.plusMinutes(30L * i).toString(), appointment);
        }
        timetable.setAppointments(appointments);

        timetableRepository.save(timetable);
        return TimetableDto.from(timetable);
    }

    @Override
    public TimetableDto updateTimetable(long id, long hospitalId, long doctorId, LocalDateTime from, LocalDateTime to, String room) {
        Timetable timetable = timetable(id);
        if (timetable.getAppointments().values().stream().map(Objects::nonNull).findAny().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Modify timetable with appointments isn`t allowed"
            );
        }

        Timetable newTimetable = Timetable
                .builder()
                .id(id)
                .hospitalId(hospitalId)
                .doctorId(doctorId)
                .fromTime(from)
                .toTime(to)
                .room(room)
                .build();

        timetableRepository.save(newTimetable);
        return TimetableDto.from(newTimetable);
    }

    @Override
    public void softDeleteTimetable(long id) {
        Timetable timetable = timetable(id);
        timetable.setState(TimetableState.deleted);
        timetableRepository.save(timetable);
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

    //Простите пожалуйтса за такой код, на проде я бы так не писал, просто очень времени не хватало, потому что поздно начал решать задание
    @Override
    public Collection<LocalDateTime> freeTickets(long timetableId) {
        Timetable timetable = timetable(timetableId);
        Collection<LocalDateTime> freeTime = new ArrayList<>();
        for (LocalDateTime time : timetable.getAppointments().keySet().stream().map(LocalDateTime::parse).toList()) {
            if (timetable.getAppointments().get(time.toString()).getState() == AppointmentState.free) {
                freeTime.add(time);
            }
        }

        return freeTime;
    }

    @Override
    public void timetableSetAppointment(long timetableId, LocalDateTime time, Appointment value) {
        Timetable timetable = timetable(timetableId);

        if (!timetable.getAppointments().containsKey(time.toString())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Time " + time + " isn`t available for this appointment"
            );
        }

        if (timetable.getAppointments().get(time.toString()) != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "This time is already have appointment"
            );
        }

        timetable.getAppointments().put(time.toString(), value);

        timetableRepository.save(timetable);
    }
}

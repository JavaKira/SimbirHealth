package ru.vcodetsev.timetable.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.vcodetsev.timetable.timetable.Timetable;
import ru.vcodetsev.timetable.timetable.TimetableService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final TimetableService timetableService;

    @Override
    public AppointmentDto createAppointment(long timetableId, LocalDateTime time) {
        Timetable timetable = timetableService.timetable(timetableId);
        Appointment appointment = Appointment
                .builder()
                .timetableId(timetableId)
                .time(time)
                .build();

        timetableService.timetableSetAppointment(timetableId, time, appointment);

        appointmentRepository.save(appointment);
        return AppointmentDto.from(appointment);
    }

    @Override
    public void softDeleteAppointment(long appointmentId) {
        Appointment appointment = appointment(appointmentId);
        appointment.setState(AppointmentState.deleted);
        appointmentRepository.save(appointment);
    }

    @Override
    public Collection<AppointmentDto> getAccountAppointments(long AccountId) {
        return List.of(); //todo
    }

    @Override
    public Appointment appointment(long appointmentId) {
        Optional<Appointment> hospitalOptional = appointmentRepository.findById(appointmentId);
        if (hospitalOptional.isEmpty() || hospitalOptional.get().getState() == AppointmentState.deleted)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Appointment with id %d doesnt exist".formatted(appointmentId)
            );

        return hospitalOptional.get();
    }

    @Override
    public Collection<Appointment> allAppointments() {
        return null; //todo
    }
}

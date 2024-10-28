package ru.vcodetsev.timetable.appointment;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Collection;

public interface AppointmentService {
    AppointmentDto createAppointment(long timetableId, LocalDateTime time);

    void softDeleteAppointment(long appointmentId);

    Collection<AppointmentDto> getAccountAppointments(long accountId);

    Appointment appointment(long appointmentId);

    Collection<Appointment> allAppointments();
}

package ru.vcodetsev.timetable.timetable;

import ru.vcodetsev.timetable.appointment.Appointment;
import ru.vcodetsev.timetable.appointment.AppointmentDto;

import java.time.LocalDateTime;
import java.util.Collection;

public interface TimetableService {
    Timetable timetable(long timetableId);

    TimetableDto timetableDto(long timetableId);

    TimetableDto createTimetable(long hospitalId, long doctorId, LocalDateTime from, LocalDateTime to, String room);

    TimetableDto updateTimetable(long timetableId, long hospitalId, long doctorId, LocalDateTime from, LocalDateTime to, String room);

    void softDeleteTimetable(long timetableId);

    HospitalTimetableResponse generateHospitalTimetable(long hospitalId);

    void softDeleteDoctorTimetables(long doctorId);

    void softDeleteHospitalTimetables(long hospitalId);

    DoctorTimetableResponse generateDoctorTimetable(long doctorId);

    RoomTimetableDto generateRoomTimetable(long hospitalId, String room);

    Collection<LocalDateTime> freeTickets(long timetableId);

    void timetableSetAppointment(long timetableId, LocalDateTime time, Appointment value);
}

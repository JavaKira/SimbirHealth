package ru.vcodetsev.timetable.timetable;

import java.time.LocalDateTime;

public interface TimetableService {
    Timetable timetable(long timetableId);

    TimetableDto timetableDto(long timetableId);

    TimetableDto createTimetable(long hospitalId, long doctorId, LocalDateTime from, LocalDateTime to, String room);

    HospitalTimetableResponse createHospitalTimetable(long id);
}

package ru.vcodetsev.timetable.timetable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalTimetableResponse {
    private long hospitalId;
    //todoCollection<DoctorTimetableResponse> doctorTimetableResponse;
    Collection<Timetable> timetables;
}

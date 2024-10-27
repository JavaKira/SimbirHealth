package ru.vcodetsev.timetable.timetable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorTimetableResponse {
    private long doctorId;
    private String doctorFirstName;
    private String doctorLastName;
    private Collection<DoctorRoomResponse> doctorRooms;
}

package ru.vcodetsev.timetable.timetable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRoomResponse {
    private String roomName;
    private LocalDateTime from;
    private LocalDateTime to;
}

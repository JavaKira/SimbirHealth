package ru.vcodetsev.timetable.timetable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimetableUpdateRequest {
    private long hospitalId;
    private long doctorId;
    private LocalDateTime from;
    private LocalDateTime to;
    private String room;
}
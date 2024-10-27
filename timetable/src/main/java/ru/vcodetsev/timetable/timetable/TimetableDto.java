package ru.vcodetsev.timetable.timetable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vcodetsev.timetable.appointment.AppointmentState;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimetableDto {
    private Long id;
    private long hospitalId;
    private long doctorId;
    private LocalDateTime from;
    private LocalDateTime to;
    private String room;

    public static TimetableDto from(Timetable timetable) {
        return TimetableDto
                .builder()
                .id(timetable.getId())
                .hospitalId(timetable.getHospitalId())
                .doctorId(timetable.getDoctorId())
                .from(timetable.getFrom())
                .to(timetable.getTo())
                .room(timetable.getRoom())
                .build();
    }
}

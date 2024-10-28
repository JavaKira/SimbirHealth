package ru.vcodetsev.timetable.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
    private Long id;
    private Long timetableId;
    private LocalDateTime time;

    public static AppointmentDto from(Appointment appointment) {
        return AppointmentDto
                .builder()
                .id(appointment.getId())
                .timetableId(appointment.getTimetableId())
                .time(appointment.getTime())
                .build();
    }
}

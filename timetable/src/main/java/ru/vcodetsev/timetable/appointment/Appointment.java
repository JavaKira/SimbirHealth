package ru.vcodetsev.timetable.appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vcodetsev.timetable.timetable.Timetable;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;
    private Long timetableId;
    private LocalDateTime time;

    @Enumerated(value = EnumType.STRING)
    private AppointmentState state = AppointmentState.normal;
}

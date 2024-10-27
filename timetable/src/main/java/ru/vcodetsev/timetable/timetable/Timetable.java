package ru.vcodetsev.timetable.timetable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vcodetsev.timetable.appointment.AppointmentState;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Timetable {
    @Id
    @GeneratedValue
    private Long id;
    private long hospitalId;
    private long doctorId;
    private LocalDateTime from;//todo мб как строку хранить
    private LocalDateTime to;
    private String room;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private TimetableState state = TimetableState.normal;
}


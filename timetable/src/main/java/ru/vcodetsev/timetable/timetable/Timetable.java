package ru.vcodetsev.timetable.timetable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vcodetsev.timetable.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.Map;

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
    @OneToMany
    private Map<LocalDateTime, Appointment> appointments;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private TimetableState state = TimetableState.normal;
}


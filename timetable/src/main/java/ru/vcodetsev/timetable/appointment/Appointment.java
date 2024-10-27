package ru.vcodetsev.timetable.appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue
    private Long id;
    private long hospitalId;
    private long doctorId;
    private LocalDateTime from;
    private LocalDateTime to;
    private String room;

    @Enumerated(value = EnumType.STRING)
    private AppointmentState state = AppointmentState.normal;
}

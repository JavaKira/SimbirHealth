package ru.vcodetsev.timetable.timetable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import ru.vcodetsev.timetable.appointment.Appointment;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "timetables")
public class Timetable {
    @Id
    @GeneratedValue
    private Long id;
    private long hospitalId;
    private long doctorId;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
    private String room;
    @ManyToMany
    private Map<String, Appointment> appointments;

    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    private TimetableState state = TimetableState.normal;
}


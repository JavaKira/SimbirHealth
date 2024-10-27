package ru.vcodetsev.document.history;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitHistory {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime date;
    private long patientId;
    private long hospitalId;
    private long doctorId;
    private String room;
    private String data;
}

package ru.vcodetsev.document.history;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitHistoryCreateRequest {
    private LocalDateTime date;
    private long patientId;
    private long hospitalId;
    private long doctorId;
    private String room;
    private String data;
}

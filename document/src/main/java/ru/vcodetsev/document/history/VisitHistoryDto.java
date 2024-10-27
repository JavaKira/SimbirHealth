package ru.vcodetsev.document.history;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VisitHistoryDto {
    private Long id;
    private LocalDateTime date;
    private long patientId;
    private long hospitalId;
    private long doctorId;
    private String room;
    private String data;

    public static VisitHistoryDto from(VisitHistory visitHistory) {
        return VisitHistoryDto
                .builder()
                .id(visitHistory.getId())
                .date(visitHistory.getDate())
                .patientId(visitHistory.getPatientId())
                .hospitalId(visitHistory.getHospitalId())
                .doctorId(visitHistory.getDoctorId())
                .room(visitHistory.getRoom())
                .data(visitHistory.getData())
                .build();
    }
}

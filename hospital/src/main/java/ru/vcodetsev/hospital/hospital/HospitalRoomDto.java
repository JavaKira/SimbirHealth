package ru.vcodetsev.hospital.hospital;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalRoomDto {
    private long id;
    private String name;

    public static HospitalRoomDto from(HospitalRoom hospitalRoom) {
        return HospitalRoomDto
                .builder()
                .id(hospitalRoom.getId())
                .name(hospitalRoom.getName())
                .build();
    }
}

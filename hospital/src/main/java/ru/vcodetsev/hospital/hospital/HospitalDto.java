package ru.vcodetsev.hospital.hospital;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDto {
    private Long id;
    private String name;
    private String address;
    private String phone;

    public static HospitalDto from(Hospital hospital) {
        return HospitalDto
                .builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .address(hospital.getAddress())
                .phone(hospital.getPhone())
                .build();
    }
}

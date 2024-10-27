package ru.vcodetsev.hospital.hospital.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HospitalCreateRequest {
    private String name;
    private String address;
    private String phone;
    private String[] rooms;
}

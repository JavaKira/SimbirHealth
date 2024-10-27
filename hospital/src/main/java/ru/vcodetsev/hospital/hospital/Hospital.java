package ru.vcodetsev.hospital.hospital;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    private HospitalState state = HospitalState.normal;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<HospitalRoom> rooms;
//    Можно было бы добавить
//    private String email;
//    private String website;
//    private String description;
}

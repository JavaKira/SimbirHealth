package ru.vcodetsev.hospital.hospital;

import java.util.Collection;

public interface HospitalService {
    Hospital hospital(long hospitalId);

    HospitalDto hospitalDto(long hospitalId);

    Collection<HospitalDto> allHospitalDtos(int from, int count);

    Collection<HospitalRoomDto> hospitalRoomsDtos(long id);

    HospitalDto createHospital(String name, String address, String phone, String[] rooms);

    HospitalDto putHospital(long id, String name, String address, String phone, String[] rooms);

    void softDelete(long id);
}

package ru.vcodetsev.hospital.hospital;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {
    final HospitalRepository repository;

    @Override
    public Hospital hospital(long hospitalId) {
        Optional<Hospital> hospitalOptional = repository.findById(hospitalId);
        if (hospitalOptional.isEmpty() || hospitalOptional.get().getState() == HospitalState.deleted)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Hospital with id %d doesnt exist".formatted(hospitalId)
            );

        return hospitalOptional.get();
    }

    @Override
    public HospitalDto hospitalDto(long hospitalId) {
        return HospitalDto.from(hospital(hospitalId));
    }

    @Override
    public List<HospitalDto> allHospitalDtos(int from, int count) {
        List<HospitalDto> hospitalDtos = repository
                .findAll()
                .stream()
                .filter(hospital -> hospital.getState() != HospitalState.deleted)
                .map(HospitalDto::from)
                .collect(Collectors.toList());
        return hospitalDtos
                .subList(from, Math.min(from + count, hospitalDtos.size()));
    }

    @Override
    public Collection<HospitalRoomDto> hospitalRoomsDtos(long id) {
        return hospital(id)
                .getRooms()
                .stream()
                .map(HospitalRoomDto::from)
                .toList();
    }

    @Override
    public HospitalDto createHospital(String name, String address, String phone, String[] rooms) {
        Collection<HospitalRoom> roomsCollection = Stream.of(rooms)
                .map(s -> HospitalRoom.builder().name(s).build())
                .toList();

        Hospital newHospital = Hospital.builder()
                .name(name)
                .address(address)
                .phone(phone)
                .rooms(roomsCollection)
                .state(HospitalState.normal)
                .build();
        repository.save(newHospital);
        return HospitalDto.from(newHospital);
    }

    @Override
    public HospitalDto putHospital(long id, String name, String address, String phone, String[] rooms) {
        Hospital hospital = hospital(id);

        Collection<HospitalRoom> roomsCollection = Stream.of(rooms)
                .map(s -> HospitalRoom.builder().name(s).build())
                .toList();

        Hospital newHospital = Hospital.builder()
                .id(hospital.getId())
                .name(name)
                .address(address)
                .phone(phone)
                .rooms(roomsCollection)
                .state(HospitalState.normal)
                .build();
        repository.save(newHospital);
        return HospitalDto.from(newHospital);
    }

    @Override
    public void softDelete(long id) {
        Hospital hospital = hospital(id);
        hospital.setState(HospitalState.deleted);
        repository.save(hospital);
    }
}

package ru.vcodetsev.hospital.hospital.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vcodetsev.hospital.hospital.HospitalDto;
import ru.vcodetsev.hospital.hospital.HospitalService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Hospitals/Admin")
class HospitalAdminController {
    final HospitalService hospitalService;

    @PostMapping
    @Operation(
            summary = "Создание записи о новой больнице",
            description = "Только администраторы"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    HospitalDto createHospital(@RequestBody HospitalCreateRequest request) {
        return hospitalService.createHospital(
                request.getName(),
                request.getAddress(),
                request.getPhone(),
                request.getRooms()
        );
    }

    //todo по сути put это создание, нужно использовать createRequest
    @PutMapping("/{id}")
    @Operation(
            summary = "Изменение информации о больнице по Id",
            description = "Только администраторы"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    HospitalDto updateHospital(@RequestBody HospitalUpdateRequest request, @PathVariable long id) {
        return hospitalService.putHospital(
                id,
                request.getName(),
                request.getAddress(),
                request.getPhone(),
                request.getRooms()
        );
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Мягкое удаление записи о больнице",
            description = "Только администраторы"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    void deleteHospital(@PathVariable long id) {
        hospitalService.softDelete(id);
    }
}

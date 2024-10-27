package ru.vcodetsev.hospital.hospital;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Hospitals")
class HospitalController {
    final HospitalService hospitalService;

    @GetMapping
    @Operation(
            summary = "Получение списка больниц",
            description = "Только авторизованные пользователи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    Collection<HospitalDto> hospitals(int from, int count) { //todo мб тут нужно возращать только айдишники больниц
        return hospitalService.allHospitalDtos(from, count);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение информации о больнице по Id",
            description = "Только авторизованные пользователи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    HospitalDto hospital(@PathVariable long id) {
        return hospitalService.hospitalDto(id);
    }

    @GetMapping("/{id}/Rooms")
    @Operation(
            summary = "Получение списка кабинетов больницы по Id",
            description = "Только авторизованные пользователи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    Collection<HospitalRoomDto> hospitalRooms(@PathVariable long id) {
        return hospitalService.hospitalRoomsDtos(id);
    }
}

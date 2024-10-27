package ru.vcodetsev.account.doctor;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vcodetsev.account.account.AccountDto;
import ru.vcodetsev.account.account.AccountService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Doctors")
class DoctorController {
    final AccountService service;

    @GetMapping
    @Operation(
            summary = "Получение списка докторов",
            description = "Только авторизованные пользователи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    Collection<AccountDto> getDoctors(
            @RequestParam(required = false) String nameFilter,
            int from,
            int count
    ) {
        return service.getDoctorsInfo(nameFilter, from, count);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение информации о докторе по id",
            description = "Только авторизованные пользователи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    AccountDto getDoctor(@PathVariable("id") long id) {
        return service.doctorInfo(id);
    }
}

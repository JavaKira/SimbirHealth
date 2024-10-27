package ru.vcodetsev.document.history;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping("/History")
class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/Account/{id}")
    @Operation(
            summary = "Получение истории посещений и назначений аккаунта",
            description = "Возвращает записи где {pacientId}={id}\n" +
                    "Только врачи и аккаунт, которому принадлежит история"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    Collection<Long> getAccount(@PathVariable long id) {
        return historyService.getAllByAccount(id);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Получение подробной информации о посещении и назначениях",
            description = "Только врачи и аккаунт, которому принадлежит история"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    VisitHistoryDto getHistory(@PathVariable long id) {
        return historyService.getInfoById(id);
    }

    @PostMapping
    @Operation(
            summary = "Создание истории посещения и назначения",
            description = "Только администраторы и менеджеры и врачи. {pacientId} - с ролью User."
    )
    @SecurityRequirement(name = "Bearer Authentication")
    VisitHistoryDto createHistory(@RequestBody VisitHistoryCreateRequest request) {
        return historyService.create(
                request.getDate(), //todo наверное должно быть кратно 30 минутам и без секунд
                request.getPatientId(),
                request.getHospitalId(),
                request.getDoctorId(),
                request.getRoom(),
                request.getData()
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление истории посещения и назначения",
            description = "Только администраторы и менеджеры и врачи. {pacientId} - с ролью User."
    )
    @SecurityRequirement(name = "Bearer Authentication")
    VisitHistoryDto updateHistory(@PathVariable long id, @RequestBody VisitHistoryUpdateRequest request) {
        return historyService.update(
                id,
                request.getDate(),
                request.getPatientId(),
                request.getHospitalId(),
                request.getDoctorId(),
                request.getRoom(),
                request.getData()
        );
    }
}

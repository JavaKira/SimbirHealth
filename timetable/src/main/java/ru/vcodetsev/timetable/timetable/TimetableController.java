package ru.vcodetsev.timetable.timetable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vcodetsev.timetable.appointment.AppointmentCreateRequest;
import ru.vcodetsev.timetable.appointment.AppointmentDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Timetable")
class TimetableController {
    final TimetableService timetableService;

    @PostMapping
    @Operation(
            summary = "Создание новой записи в расписании",
            description = "Только администраторы и менеджеры. {from} и {to} - количество\n" +
                    "минут всегда кратно 30, секунды всегда 0 (пример: “2024-04-25T11:30:00Z”, “2024-\n" +
                    "04-25T12:00:00Z”). {to} > {from}. Разница между {to} и {from} не должна превышать\n" +
                    "12 часов."
    )
    @SecurityRequirement(name = "Bearer Authentication")
    TimetableDto createTimetable(@RequestBody TimetableCreateRequest request) {
        return timetableService.createTimetable(
                request.getHospitalId(),
                request.getDoctorId(),
                request.getFrom(),
                request.getTo(),
                request.getRoom()
        );
    }

    @PutMapping
    @Operation(
            summary = "Обновление записи расписания",
            description = "Только администраторы и менеджеры. Нельзя изменить если есть\n" +
                    "записавшиеся на прием. {from} и {to} - количество минут всегда кратно 30,\n" +
                    "секунды всегда 0 (пример: “2024-04-25T11:30:00Z”, “2024-04-25T12:00:00Z”). {to} >\n" +
                    "{from}. Разница между {to} и {from} не должна превышать 12 часов.\n"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    TimetableDto updateTimetable(@RequestBody TimetableUpdateRequest request) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление записи расписания",
            description = "Только администраторы и менеджеры"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    void deleteTimetable(@PathVariable("id") long id) {

    }

    @DeleteMapping("/Doctor/{id}")
    @Operation(
            summary = "Удаление записей расписания доктора",
            description = "Только администраторы и менеджеры"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    void deleteTimetableDoctor(@PathVariable("id") long id) {

    }

    @DeleteMapping("/Hospital/{id}")
    @Operation(
            summary = "Удаление записей расписания больницы ",
            description = "Только администраторы и менеджеры"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    void deleteTimetableHospital(@PathVariable("id") long id) {

    }

    @GetMapping("/Hospital/{id}")
    @Operation(
            summary = "Получение расписания больницы по Id",
            description = "Только авторизованные пользователи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    HospitalTimetableResponse getTimetableHospital(@PathVariable("id") long id) {
        return timetableService.createHospitalTimetable(id);
    }

    @GetMapping("/Doctor/{id}")
    @Operation(
            summary = "Получение расписания врача по Id",
            description = "Только авторизованные пользователи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    TimetableDto getTimetableDoctor(@PathVariable("id") long id) {
        return null;
    }

    @GetMapping("/Hospital/{id}/Room/{room}")
    @Operation(
            summary = "Получение расписания кабинета больницы",
            description = "Только администраторы и менеджеры и врачи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    TimetableDto getTimetableRoom(@PathVariable("id") long id, @PathVariable("room") String room) {
        return null;
    }

    @GetMapping("/{id}/Appointments")
    @Operation(
            summary = "Получение свободных талонов на приём.",
            description = "Только авторизованные пользователи\n" +
                    "Каждые 30 минут из записи расписания - это один талон. Если в\n" +
                    "сущности Timetable from=2024-04-25T11:00:00Z, to=2024-04-25T12:30:00Z, то\n" +
                    "14\n" +
                    "запись доступна на: 2024-04-25T11:00:00Z, 2024-04-25T11:30:00Z, 2024-04-\n" +
                    "25T12:00:00Z.\n"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    void getTicket(@PathVariable("id") long id) {

    }

    @PostMapping("/{id}/Appointments")
    @Operation(
            summary = "Записаться на приём",
            description = "Только авторизованные пользователи"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    AppointmentDto createAppointment(@PathVariable("id") long id, AppointmentCreateRequest request) {
        return null;
    }

    @DeleteMapping("/Appointment/{id}")
    @Operation(
            summary = "Отменить запись на приём",
            description = "Только администраторы, менеджеры, и записавшийся пользователь"
    )
    @SecurityRequirement(name = "Bearer Authentication")
    void deleteAppointment(@PathVariable("id") long id) {

    }
}

package ikharipov.AOP.controllers.tracktime;

import ikharipov.AOP.model.tracktime.MethodTimeExecution;
import ikharipov.AOP.services.track_time_services.TrackTimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/track_time")
public class TrackTimeController {

    @Autowired
    private TrackTimeService trackTimeService;

    @Operation(summary = "Получение информации о времени выполнения метода")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение сущности для работы с временем выполнения методов",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MethodTimeExecution.class))
    )
    @GetMapping("/{id}")
    public MethodTimeExecution getMethodTimeExecution(@PathVariable("id") String id) {
        return trackTimeService.getMethodTimeExecutionsById(UUID.fromString(id));
    }

    @Operation(summary = "Получение информации о всех случаях выполнения метода по его наименованию")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение коллеции сущностей для работы с временем выполнения метода",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MethodTimeExecution.class))
    )
    @GetMapping("by_name/{methodName}")
    public List<MethodTimeExecution> getMethodTimeExecutionsByName(@PathVariable("methodName") String methodName) {
        return trackTimeService.getMethodTimeExecutionsByName(methodName);
    }

    @Operation(summary = "Получение максимального времени выполнения метода по его наименованию")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение максимального времени выполнения метода",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)
    )
    @GetMapping("/max/{methodName}")
    public long getMaxMethodTimeExecution(@PathVariable("methodName") String methodName) {
        List<MethodTimeExecution> methodTimeExecutions = trackTimeService.getMethodTimeExecutionsByName(methodName);
        return trackTimeService.getMaxMethodExecutionTime(methodTimeExecutions);
    }

    @Operation(summary = "Получение минимального времени выполнения метода по его наименованию")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение минимального времени выполнения метода",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)
    )
    @GetMapping("/min/{methodName}")
    public long getMinMethodTimeExecution(@PathVariable("methodName") String methodName) {
        List<MethodTimeExecution> methodTimeExecutions = trackTimeService.getMethodTimeExecutionsByName(methodName);
        return trackTimeService.getMinMethodExecutionTime(methodTimeExecutions);
    }

    @Operation(summary = "Получение среднего времени выполнения метода по его наименованию")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение среднего времени выполнения метода",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)
    )
    @GetMapping("/average/{methodName}")
    public double getAverageMethodTimeExecution(@PathVariable("methodName") String methodName) {
        List<MethodTimeExecution> methodTimeExecutions = trackTimeService.getMethodTimeExecutionsByName(methodName);
        return trackTimeService.getAverageMethodExecutionTime(methodTimeExecutions);
    }
}
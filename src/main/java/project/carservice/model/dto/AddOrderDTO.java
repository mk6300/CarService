package project.carservice.model.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import project.carservice.model.entity.Car;


import java.time.LocalDate;

public class AddOrderDTO {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Future(message = "Date must be in future!")
    private LocalDate date;

    @Size(min = 20, message = "Description must be at least 20 characters!")
    @NotNull
    private String description;

    @NotNull
    private Car car;
}

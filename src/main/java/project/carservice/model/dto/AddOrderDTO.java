package project.carservice.model.dto;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import project.carservice.model.entity.Car;


import java.time.LocalDate;
import java.util.UUID;

public class AddOrderDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Date must be in future!")
    @NotNull(message = "Please select date!")
    private LocalDate date;

    @Size(min = 20, message = "Description must be at least 20 characters!")
    @NotNull
    private String description;

    @NotNull(message = "Select car from garage or add a new car")
    private UUID id;


    public AddOrderDTO(LocalDate date, String description, UUID id) {
        this.date = date;
        this.description = description;
        this.id = id;
    }

    public AddOrderDTO() {
    }


    public LocalDate getDate() {
        return date;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static Object empty() {
        return new AddOrderDTO(null, null, null);
    }
}
package project.carservice.model.dto.addDTO;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;
import java.util.UUID;

public class AddOrderDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Date must be in future!")
    @NotNull(message = "Please select date!")
    private LocalDate date;

    @Size(min = 20, message = "Description must be at least 20 characters!")
    @NotNull (message = "Please enter description!")
    private String description;

    @NotNull(message = "Select car from garage or add a new car")
    private UUID carId;

public AddOrderDTO(LocalDate date, String description, UUID carId) {
        this.date = date;
        this.description = description;
        this.carId = carId;
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

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public static Object empty() {
        return new AddOrderDTO(null, null, null);
    }
}
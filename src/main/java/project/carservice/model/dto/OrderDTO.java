package project.carservice.model.dto;

import java.time.LocalDate;
import java.util.UUID;

public class OrderDTO {
    private UUID id;
    private LocalDate date;
    private String description;
    private String status;
    private CarDTO car;
    private UserDTO mechanic;
    public OrderDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public UserDTO getMechanic() {
        return mechanic;
    }

    public void setMechanic(UserDTO mechanic) {
        this.mechanic = mechanic;
    }
}

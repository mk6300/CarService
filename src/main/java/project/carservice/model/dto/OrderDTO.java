package project.carservice.model.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class OrderDTO {
    private UUID id;
    private LocalDate date;
    private String description;
    private String status;
    private CarDTO car;
    private UserDTO mechanic;
    private String mechanicComment;

    private List<Long> partId;

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

    public String getMechanicComment() {
        return mechanicComment;
    }

    public void setMechanicComment(String mechanicComment) {
        this.mechanicComment = mechanicComment;
    }

    public List<Long> getPartId() {
        return partId;
    }

    public void setPartId(List<Long> partId) {
        this.partId = partId;
    }
}

package project.carservice.model.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

public class AddPartDTO {
    @NotNull
    @Size(min = 3, max = 30, message = "Name must be between 3 and 255 characters")
    private String name;
    @NotNull (message = "Price must be positive number")
    @PositiveOrZero(message = "Price must be positive number")
    private Double price;
    @NotNull(message = "You must choose supplier")
    private UUID supplierId;
    @NotNull
    @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
    private String description;


    public String getName() {
        return name;
    }

    public AddPartDTO() {
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public UUID getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(UUID supplierId) {
        this.supplierId = supplierId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

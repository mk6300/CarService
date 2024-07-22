package project.carservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class AddPartDTO {
    @NotNull
    @Size(min = 3, max = 30, message = "Name must be between 3 and 255 characters")
    private String name;
    @NotNull
    @Positive(message = "Price must be positive number")
    private Double price;
    @NotNull (message = "You must choose supplier")
    private UUID supplierId;
    @NotNull
    @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
    private String description;

    public String getName() {
        return name;
    }

    public AddPartDTO() {
    }

    public double getPrice() {
        return price;
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
}

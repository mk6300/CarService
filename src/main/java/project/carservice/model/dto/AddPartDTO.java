package project.carservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AddPartDTO {
    @NotNull
    private String name;
    @NotBlank
    private Double price;
    @NotBlank
    private UUID supplierId;

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

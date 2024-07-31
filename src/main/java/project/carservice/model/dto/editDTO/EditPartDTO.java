package project.carservice.model.dto.editDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class EditPartDTO {
    private Long id;

    @NotNull (message = "Name must be between 3 and 255 characters")
    @Size(min = 3, max = 30, message = "Name must be between 3 and 255 characters")
    private String name;
    @NotNull (message = "Price must be positive number")
    @PositiveOrZero(message = "Price must be positive number")
    private Double price;
    @NotNull(message = "You must choose supplier")
    private UUID supplierId;
    @NotNull (message = "Description must be between 3 and 255 characters")
    @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
    private String description;

    public EditPartDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setDescription(String description) {
        this.description = description;
    }
}
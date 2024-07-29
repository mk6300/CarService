package project.carservice.model.dto.addDTO;

import jakarta.validation.constraints.*;

public class AddServiceDTO {

    @NotNull
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    @NotNull(message = "Price must be positive number")
    @PositiveOrZero(message = "Price must be positive number")
    private Double price;
    @NotNull
    @Size(min = 3, max = 255, message = "Description must be between 3 and 255 characters")
    private String description;

    public AddServiceDTO() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

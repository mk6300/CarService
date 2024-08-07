package project.carservice.model.dto.addDTO;

import jakarta.validation.constraints.*;

import java.util.UUID;

public class AddPartDTO {
    @NotNull (message = "{add.part.name.not.empty}")
    @Size(min = 3, max = 50, message = "{add.part.name.length}")
    private String name;
    @NotNull (message = "{add.part.price.not.null}")
    @PositiveOrZero(message = "{add.part.price.positive}")
    private Double price;
    @NotNull(message = "{add.part.supplierId.not.empty}")
    private UUID supplierId;
    @NotNull (message = "{add.part.description.not.empty}")
    @Size(min = 3, max = 255, message = "{add.part.description.length}")
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

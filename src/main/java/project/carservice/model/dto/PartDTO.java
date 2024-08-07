package project.carservice.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class PartDTO {
    private Long id;
    @NotNull(message = "{add.part.name.not.empty}")
    @Size(min = 3, max = 50, message = "{add.part.name.length}")
    private String name;
    @NotNull (message = "{add.part.price.not.null}")
    @Positive(message = "{add=part.price.positive}")
    private Double price;
    @NotNull(message = "{add.part.supplier.not.empty}")
    private UUID supplierId;
    @NotNull (message = "{add.part.description.not.empty")
    @Size(min = 3, max = 255, message = "{add.part.description.length}")
    private String description;

    public PartDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

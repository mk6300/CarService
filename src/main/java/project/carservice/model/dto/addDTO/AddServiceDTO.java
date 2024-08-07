package project.carservice.model.dto.addDTO;

import jakarta.validation.constraints.*;

public class AddServiceDTO {

    @NotNull (message = "{add.service.name.not.empty]")
    @Size(min = 3, max = 50, message = "{add.service.name.length}")
    private String name;
    @NotNull(message = "{add.service.price.not.null}")
    @Positive(message = "{add.service.price.positive}")
    private Double price;
    @NotNull (message = "{add.service.description.not.empty}")
    @Size(min = 3, max = 255, message = "{add.service.description.length}")
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

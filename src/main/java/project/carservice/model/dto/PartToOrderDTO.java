package project.carservice.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PartToOrderDTO {
    @NotNull (message = "You must choose spare part")
    private Long selectedPartId;
    @Positive (message = "You must choose quantity")
    @NotNull (message = "You must choose quantity")
    private Integer quantity;

    public PartToOrderDTO() {
    }

    public Long getSelectedPartId() {
        return selectedPartId;
    }

    public void setSelectedPartId(Long selectedPartId) {
        this.selectedPartId = selectedPartId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

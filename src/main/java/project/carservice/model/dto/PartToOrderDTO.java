package project.carservice.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PartToOrderDTO {
    @NotNull (message = "{add.partToOrder.selected.not.empty}")
    private Long selectedPartId;
    @Positive (message = "{add.partToOrder.quantity.positive}")
    @NotNull (message = "{add.partToOrder.quantity.notnull}")
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

package project.carservice.model.dto;

import java.util.List;
import java.util.UUID;

public class EditOrderDTO {

    private UUID id;
    private String description;
    private String status;
    private UUID mechanicId;
    private String mechanicComment;
    private List<Long> partId;

    public EditOrderDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(UUID mechanicId) {
        this.mechanicId = mechanicId;
    }

    public static Object empty() {
        return new EditOrderDTO();
    }

    public String getMechanicComment() {
        return mechanicComment;
    }

    public void setMechanicComment(String mechanicComment) {
        this.mechanicComment = mechanicComment;
    }

    public List<Long> getPartId() {
        return partId;
    }

    public void setPartId(List<Long> partId) {
        this.partId = partId;
    }
}

package project.carservice.model.entity.enums;
public enum OrdersStatusEnum {
    SCHEDULED ("Scheduled"),
    IN_PROGRESS ("In Progress"),
    FINISHED ("Finished");
    private final String displayName;

    OrdersStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }


}

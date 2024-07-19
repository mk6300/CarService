package project.carservice.model.dto.AddDTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SubscriberDTO {
    @NotNull ()
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$" , message = "Please enter valid email")
    private String subsEmail;

    public String getSubsEmail() {
        return subsEmail;
    }

    public void setSubsEmail(String subsEmail) {
        this.subsEmail = subsEmail;
    }
}

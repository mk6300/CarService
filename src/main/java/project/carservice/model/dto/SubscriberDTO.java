package project.carservice.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import project.carservice.validation.annotations.UniqueEmail;
import project.carservice.validation.annotations.UniqueEmailSubscriber;

public class SubscriberDTO {
    @NotNull(message = "Email cannot be empty")
    @UniqueEmailSubscriber
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Please enter valid email")
    private String subsEmail;

    public String getSubsEmail() {
        return subsEmail;
    }

    public void setSubsEmail(String subsEmail) {
        this.subsEmail = subsEmail;
    }
}

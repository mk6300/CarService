package project.carservice.model.dto.editDTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


import java.util.UUID;


public class EditUserDTO {
    private UUID id;

    private String username;
    @NotEmpty (message = "{register.firstName.not.empty}")
    @Size(min = 2, max = 15, message = "{register.firstName.length}")
    private String firstName;
    @NotEmpty (message = "{register.lastName.not.empty}")
    @Size(min = 2, max = 15, message = "{register.lastName.length}")
    private String lastName;
    @NotEmpty (message = "{register.email.not.empty}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{register.email.valid}")
    private String email;
    @NotEmpty (message = "{register.phone.not.empty}")
    @Pattern(regexp = "^([+]?359)|0?(|-| )8[789]\\d{1}(|-| )\\d{3}(|-| )\\d{3}$", message = "{register.phone.valid}")
    private String phone;

    public EditUserDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

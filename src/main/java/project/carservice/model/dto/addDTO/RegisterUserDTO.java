package project.carservice.model.dto.addDTO;

import jakarta.validation.constraints.*;
import project.carservice.validation.annotations.UniqueEmail;
import project.carservice.validation.annotations.UniqueUsername;

public class RegisterUserDTO {

    @UniqueUsername (message = "{register.username.unique}")
    @Size(min = 3, max = 20, message = "{register.username.length}")
    @NotNull (message = "{register.username.not.empty}")
    private String username;
    @Size(min = 5, max = 20, message = "{register.password.length}")
    @NotNull (message = "{register.password.not.empty}")
    private String password;
    @NotBlank (message = "{register.firstName.not.empty}")
    @Size(min = 2, max = 15, message = "{register.firstName.length}")
    private String firstName;
    @NotBlank (message = "{register.lastName.not.empty}")
    @Size(min = 2, max = 15, message = "{register.lastName.length}")
    private String lastName;
    @UniqueEmail (message = "{register.email.unique}")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{register.email.valid}")
    @NotBlank(message = "{register.email.not.empty}")
    private String email;
    @NotNull (message = "{register.phone.not.empty}")
    @Pattern (regexp="^([+]?359)|0?(|-| )8[789]\\d{1}(|-| )\\d{3}(|-| )\\d{3}$", message = "{register.phone.valid}")
    private String phone;
    @NotNull (message = "{register.confirmPassword.not.empty}")
    private String confirmPassword;

    public RegisterUserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}

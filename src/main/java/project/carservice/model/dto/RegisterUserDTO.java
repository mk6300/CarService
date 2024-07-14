package project.carservice.model.dto;

import jakarta.validation.constraints.*;
import project.carservice.validation.UniqueEmail;
import project.carservice.validation.UniqueUsername;
import project.carservice.validation.ValidPhoneNumber;

public class RegisterUserDTO {

    @UniqueUsername
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @NotNull
    private String username;
    @Size(min = 3, max = 20, message = "Password length must be between 5 and 20 characters!")
    @NotNull
    private String password;
    @NotBlank
    @Size(min = 2, max = 15, message = "Name length must be between 2 and 15 characters!")
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 15, message = "Name length must be between 2 and 15 characters!")
    private String lastName;
    @UniqueEmail
    @Email(message = "Enter valid email!")
    @NotBlank(message = "Email cannot be empty!")
    private String email;
    @Pattern (regexp="^([+]?359)|0?(|-| )8[789]\\d{1}(|-| )\\d{3}(|-| )\\d{3}$", message = "Need add valid phone number")
    private String phone;
    @NotNull
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

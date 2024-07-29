package project.carservice.model.dto.addDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import project.carservice.validation.annotations.ValidPhoneNumber;

public class AddSupplierDTO {

    @NotNull
    @Size (min=3, message = "Name must be at least 3 characters")
    private String name;
    @NotNull
    @Size (min=5, message = "Address must be at least 10 characters")
    private String address;
    @NotNull
    @ValidPhoneNumber (message = "Please enter valid phone number")
    private String phoneNumber;
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$", message = "Please enter valid email")
    private String email;

    private String information;

    public AddSupplierDTO() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public static AddSupplierDTO empty() {
        return new AddSupplierDTO();
    }
}

package project.carservice.model.dto.addDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import project.carservice.validation.annotations.ValidPhoneNumber;

public class AddSupplierDTO {

    @NotNull (message = "{add.supplier.name.not.empty}")
    @Size (min=3, message = "{add.supplier.name.length}")
    private String name;
    @NotNull (message = "{add.supplier.address.not.empty}")
    @Size (min=5, message = "{add.supplier.address.length}")
    private String address;
    @NotNull (message = "{add.supplier.phone.not.empty}")
    @ValidPhoneNumber (message = "{add.supplier.phone.valid}")
    private String phoneNumber;
    @NotNull (message = "{add.supplier.email.not.empty}")
    @Pattern(regexp = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$", message = "{add.supplier.email.valid}")
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

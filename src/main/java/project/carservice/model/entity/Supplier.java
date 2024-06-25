package project.carservice.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name = "suppliers")
public class Supplier extends BaseEntity{

    @Column (nullable = false,unique = true)
    private String name;
    @Column (nullable = false)
    private String address;
    @Column (nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column
    private String information;
    @OneToMany(mappedBy = "supplier")
    private Set<Part> parts;

    public Supplier() {
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

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}

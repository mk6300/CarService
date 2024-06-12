package project.carservice.model.entity;

import jakarta.persistence.*;
import project.carservice.model.entity.enums.EngineTypeEnum;

import java.util.List;

@Entity
@Table (name="cars")
public class Car extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String registration;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EngineTypeEnum engine;

    @Column(nullable = false, unique = true)
    private String vinNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "part_id", referencedColumnName = "id")
    private Parts part;

    @OneToMany(mappedBy = "car")
    private List<Order> orders;


    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setEngine(EngineTypeEnum engine) {
        this.engine = engine;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setPart(Parts part) {
        this.part = part;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getRegistration() {
        return registration;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public EngineTypeEnum getEngine() {
        return engine;
    }

    public String getVinNumber() {
        return vinNumber;
    }
}

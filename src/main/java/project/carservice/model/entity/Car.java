package project.carservice.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import project.carservice.model.entity.enums.EngineTypeEnum;

import java.util.List;
import java.util.UUID;

@Entity
@Table (name="cars")
public class Car extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String registration;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column
    private int year;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EngineTypeEnum engine;

    @Column(nullable = false, unique = true)
    private String vinNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;


    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public void setMake(String make) {
        this.make = make;
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

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getRegistration() {
        return registration;
    }

    public String getMake() {
        return make;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public User getOwner() {
        return owner;
    }

    public List<Order> getOrders() {
        return orders;
    }
}

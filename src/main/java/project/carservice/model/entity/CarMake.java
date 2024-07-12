package project.carservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import project.carservice.model.entity.BaseEntity;

@Entity
@Table(name = "car_makes")
public class CarMake extends BaseEntity {
    @Column(name = "make_name")
    private String make;

    private String country;

    public CarMake() {
    }

    public String getBrand() {
        return make;
    }

    public void setBrand(String brand) {
        this.make = make;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

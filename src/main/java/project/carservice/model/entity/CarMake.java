package project.carservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "car_makes")
public class CarMake extends BaseEntity {
    @Column(name = "make_name", unique = true, nullable = true)

    private String name;
    @Column
    private String country;

    public CarMake() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

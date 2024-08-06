package project.carservice.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "used_parts")
public class UsedPart extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @Column (nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order order;

    public UsedPart() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

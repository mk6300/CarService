package project.carservice.model.entity;

import jakarta.persistence.*;
import project.carservice.model.entity.enums.EngineTypeEnum;
import project.carservice.model.entity.enums.OrdersStatusEnum;

import java.time.LocalDate;

@Entity
@Table (name = "orders")
public class Order extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrdersStatusEnum status;

    @ManyToOne(fetch = FetchType.EAGER)
    private User addedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    private User responsibleMechanic;

    @ManyToOne
    private Car car;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public User getResponsibleMechanic() {
        return responsibleMechanic;
    }

    public void setResponsibleMechanic(User responsibleMechanic) {
        this.responsibleMechanic = responsibleMechanic;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public OrdersStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrdersStatusEnum status) {
        this.status = status;
    }
}

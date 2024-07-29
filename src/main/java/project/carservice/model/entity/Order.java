package project.carservice.model.entity;

import jakarta.persistence.*;
import project.carservice.model.entity.enums.EngineTypeEnum;
import project.carservice.model.entity.enums.OrdersStatusEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String description;

    @Column
    private String mechanicComment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrdersStatusEnum status;
    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> partId;

    @ManyToOne
    private User addedBy;

    @ManyToOne
    private User responsibleMechanic;

    @ManyToOne(fetch = FetchType.EAGER)
    private Car car;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "orders_services",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<ServiceEntity> services = new ArrayList<>();

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

    public String getMechanicComment() {
        return mechanicComment;
    }

    public void setMechanicComment(String mechanicComment) {
        this.mechanicComment = mechanicComment;
    }

    public List<Long> getPartId() {
        return partId;
    }

    public void setPartId(List<Long> partId) {
        this.partId = partId;
    }

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }
}

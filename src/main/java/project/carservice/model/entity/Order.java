package project.carservice.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "orders")
public class Order extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String description;

    @Column
    private boolean isInProgress;

    @Column
    private boolean isFinished;

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

    public boolean isInProgress() {
        return isInProgress;
    }

    public void setInProgress(boolean inProgress) {
        isInProgress = inProgress;
    }

    public boolean isIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isfinished) {
        this.isFinished = isfinished;
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

}

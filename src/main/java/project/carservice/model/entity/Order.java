package project.carservice.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "orders")
public class Order extends BaseEntity {

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String information;

    @Column
    private boolean inProgress;

    @ManyToOne(fetch = FetchType.EAGER)
    private User addedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    private User responsibleMechanic;

    @ManyToOne
    private Car car;

}

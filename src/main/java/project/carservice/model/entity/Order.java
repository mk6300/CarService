package project.carservice.model.entity;

import jakarta.persistence.*;

@Entity
public class Order extends BaseEntity {

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

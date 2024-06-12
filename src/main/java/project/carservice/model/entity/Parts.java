package project.carservice.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Parts extends BaseEntity{

    @Column (nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    @OneToMany (mappedBy = "part")
    private Set<Car> cars;
}

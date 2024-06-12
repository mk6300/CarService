package project.carservice.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name = "suppliers")
public class Supplier extends BaseEntity{

    @Column (nullable = false,unique = true)
    private String name;
    @Column (nullable = false)
    private String address;
    @Column (nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;
    @Column
    private String information;
    @OneToMany(mappedBy = "supplier")
    private Set<Parts> parts;


}

package project.carservice.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name = "parts")
public class Part extends BaseEntity{

    @Column (nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

}

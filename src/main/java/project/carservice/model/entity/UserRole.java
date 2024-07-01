package project.carservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import project.carservice.model.entity.enums.UserRoleEnum;

@Entity
@Table (name="roles")
public class UserRole {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRole setRole(UserRoleEnum role) {
        this.role = role;
        return this;

    }
}

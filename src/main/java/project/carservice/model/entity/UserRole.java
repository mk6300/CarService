package project.carservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import project.carservice.model.entity.enums.UserRoleEnum;

@Entity
@Table (name="roles")
public class UserRole extends BaseEntity  {
    @NotNull
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;


    public UserRoleEnum getRole() {
        return role;
    }

    public UserRole setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}

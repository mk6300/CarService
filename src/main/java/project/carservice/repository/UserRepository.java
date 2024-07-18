package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Role;
import project.carservice.model.entity.User;
import project.carservice.model.entity.enums.UserRoleEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository <User, UUID> {
    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID uuid);

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.role = :role")
    List<User> findAllByRole(@Param("role") UserRoleEnum role);

}


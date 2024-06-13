package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
}

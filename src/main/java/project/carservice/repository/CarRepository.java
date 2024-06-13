package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Car;
@Repository
public interface CarRepository extends JpaRepository <Car, Long> {
}

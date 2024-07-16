package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Car;
import project.carservice.model.entity.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository <Car, UUID> {

    List<Car> findAllByOwner(User owner);
}

package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {

   List<Car> getAllByOwner_Username (String username);

    Optional<Car> findByRegistration(String registration);

    Optional<Car> findByVinNumber(String vinNumber);

   Optional <Car> findById (UUID id);
}

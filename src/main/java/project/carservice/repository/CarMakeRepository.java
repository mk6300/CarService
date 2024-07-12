package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.CarMake;

import java.util.UUID;
@Repository
public interface CarMakeRepository extends JpaRepository <CarMake, UUID> {
}

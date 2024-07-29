package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.carservice.model.entity.ServiceEntity;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<ServiceEntity, UUID> {
}

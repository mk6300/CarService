package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.carservice.model.entity.UsedPart;

import java.util.List;
import java.util.UUID;

public interface UsedPartRepository extends JpaRepository<UsedPart, UUID> {
    List<UsedPart> findAllByOrderId(UUID orderId);
}

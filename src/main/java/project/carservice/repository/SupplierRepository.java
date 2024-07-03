package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Supplier;

import java.util.UUID;

@Repository
public interface SupplierRepository extends JpaRepository <Supplier, UUID> {
}

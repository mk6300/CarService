package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Part;

import java.util.UUID;

@Repository
public interface PartRepository extends JpaRepository <Part, UUID> {
}

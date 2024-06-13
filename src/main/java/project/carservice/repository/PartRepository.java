package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Part;
@Repository
public interface PartRepository extends JpaRepository <Part, Long> {
}

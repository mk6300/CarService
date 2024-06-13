package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {
}

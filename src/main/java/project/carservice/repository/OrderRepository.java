package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Order;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository <Order, UUID> {


    List<Order> findAllByAddedBy_Id(UUID id);
}

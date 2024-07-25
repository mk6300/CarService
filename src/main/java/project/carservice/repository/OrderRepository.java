package project.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Order;
import project.carservice.model.entity.enums.OrdersStatusEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository <Order, UUID> {


    List<Order> findAllByAddedBy_Id(UUID id);

    List<Order> findAllByAddedBy_IdAndStatusIs(UUID id, OrdersStatusEnum status);

    List<Order> findAllByAddedBy_IdAndStatusIsNot(UUID id, OrdersStatusEnum status);
    List<Order> findAllByResponsibleMechanic_IdAndStatusNot(UUID id, OrdersStatusEnum status);

   List<Order> findAllByResponsibleMechanicIsNullOrderByDateAsc ();
}

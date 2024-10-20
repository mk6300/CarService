package project.carservice.repository;

import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.carservice.model.entity.Order;
import project.carservice.model.entity.enums.OrdersStatusEnum;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository <Order, UUID> {

    List<Order> findAllByAddedBy_IdAndStatusIsOrderByDateAsc(UUID id, OrdersStatusEnum status);

    List<Order> findAllByAddedBy_IdAndStatusIsNotOrderByDateAsc(UUID id, OrdersStatusEnum status);

    List<Order> findAllByResponsibleMechanic_IdAndStatusNotOrderByDateAsc(UUID id, OrdersStatusEnum status);

    List<Order> findAllByResponsibleMechanicIsNullOrderByDateAsc();

    List<Order> findAllByDateAndStatus(LocalDate date, OrdersStatusEnum status);

    List<Order> findAllByOrderByDateAsc();

    void deleteByDateBefore(LocalDate threeYearsAgo);

    List<Order> findAllByDateOrderByDateAsc(LocalDate date);

    List <Order> findAllByCar_RegistrationOrderByDateAsc(String registration);

    List<Order> findAllByStatusIs (OrdersStatusEnum status);
}

package project.carservice.service;


import project.carservice.model.dto.EditOrderDTO;
import project.carservice.model.dto.OrderDTO;
import project.carservice.model.dto.AddOrderDTO;


import java.util.List;
import java.util.UUID;

public interface OrderService {

    void addOrder(AddOrderDTO addOrderDTO);

    List<OrderDTO> allOrdersByUser(UUID id);

    List<OrderDTO> allOrdersByUserFinished(UUID id);

    List<OrderDTO> allOrdersByMechanic(UUID id);

    List<OrderDTO> getUnassignedOrders();

    void assignOrder(EditOrderDTO editOrderDTO);

    void removeOrder(UUID id);

}

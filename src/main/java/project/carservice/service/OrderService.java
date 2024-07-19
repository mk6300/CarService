package project.carservice.service;

import project.carservice.model.dto.AddDTOs.AddOrderDTO;
import project.carservice.model.dto.OrderDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    void addOrder(AddOrderDTO addOrderDTO);

    List<OrderDTO> allOrdersByUser(UUID id);

    List<OrderDTO> allOrdersByMechanic(UUID id);

    List<OrderDTO> getUnassignedOrders();
}

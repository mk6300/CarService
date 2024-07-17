package project.carservice.service;

import project.carservice.model.dto.AddOrderDTO;
import project.carservice.model.dto.OrderDTO;
import project.carservice.model.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    void addOrder (AddOrderDTO addOrderDTO);

    List<OrderDTO> allOrdersByUser(UUID id);
}

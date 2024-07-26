package project.carservice.service;


import project.carservice.model.dto.EditOrderDTO;
import project.carservice.model.dto.OrderDTO;
import project.carservice.model.dto.AddOrderDTO;
import project.carservice.model.dto.PartDTO;


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

    OrderDTO getOrderById(UUID id);

    void updateOrderStatusProgress(UUID id);

    void addPartToOrder(UUID id, Long partId, int quantity);

    List<PartDTO> getPartsForOrder(UUID id);

    double calculateOrderPrice(UUID id);

    void finishTask(UUID id, String mechanicComment);

    void updateOrderStatusProgress();
}

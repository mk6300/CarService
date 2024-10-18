package project.carservice.service;


import project.carservice.model.dto.ServiceDTO;
import project.carservice.model.dto.UsedPartDTO;
import project.carservice.model.dto.editDTO.EditOrderDTO;
import project.carservice.model.dto.OrderDTO;
import project.carservice.model.dto.addDTO.AddOrderDTO;
import project.carservice.model.dto.PartDTO;
import project.carservice.model.entity.Order;


import java.time.LocalDate;
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

    List<UsedPartDTO> getPartsForOrder(UUID id);

    List<ServiceDTO> getServicesForOrder(UUID id);

    double calculatePartsSumForOrder(UUID id);

    double calculateServicesSumForOrder(UUID id);

    double calculateTotalSumForOrder(UUID id);

    void finishTask(UUID id, String mechanicComment);

    void updateOrderStatus();

    void addService(UUID id, UUID serviceId);

    List<OrderDTO> allOrders();

    void deleteOldFinishedOrders();

    List<OrderDTO> findByDate(LocalDate date);

    List<OrderDTO> findByCarRegistrationNumber(String registration);
}

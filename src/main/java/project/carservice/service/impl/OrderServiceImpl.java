package project.carservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.carservice.model.dto.*;
import project.carservice.model.dto.addDTO.AddOrderDTO;
import project.carservice.model.dto.editDTO.EditOrderDTO;
import project.carservice.model.entity.Order;
import project.carservice.model.entity.ServiceEntity;
import project.carservice.model.entity.enums.OrdersStatusEnum;
import project.carservice.repository.OrderRepository;
import project.carservice.service.*;
import project.carservice.service.exceptions.OrderNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CarService carService;
    private final PartService partService;

    private final ServiceService serviceService;

    private final UsedPartService usedPartService;

    private final MailService mailService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserService userService, CarService carService, PartService partService, ServiceService serviceService, UsedPartService usedPartService, MailService mailService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.carService = carService;
        this.partService = partService;
        this.serviceService = serviceService;
        this.usedPartService = usedPartService;
        this.mailService = mailService;
    }

    @Override
    public void addOrder(AddOrderDTO addOrderDTO) {
        Order order = this.mapOrder(addOrderDTO);
        order.setStatus(OrdersStatusEnum.SCHEDULED);
        this.sendConfirmationOrderSubmit(order.getAddedBy().getEmail(), order.getCar().getModel(), order.getCar().getMake(), order.getCar().getRegistration());
        this.orderRepository.save(order);

    }

    private Order mapOrder(AddOrderDTO addOrderDTO) {
        Order order = modelMapper.map(addOrderDTO, Order.class);
        order.setCar(carService.getById(addOrderDTO.getCarId()));
        order.setAddedBy(userService.getCurrentUser());
        return order;
    }

    @Override
    public List<OrderDTO> allOrdersByUser(UUID id) {
        return this.orderRepository.findAllByAddedBy_IdAndStatusIsNotOrderByDateAsc(id, OrdersStatusEnum.FINISHED)
                .stream()
                .map(this::mapOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> allOrdersByUserFinished(UUID id) {
        return this.orderRepository.findAllByAddedBy_IdAndStatusIsOrderByDateAsc(id, OrdersStatusEnum.FINISHED)
                .stream()
                .map(this::mapOrderDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO mapOrderDTO(Order order) {
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        orderDTO.setStatus(order.getStatus().getDisplayName());
        orderDTO.setCar(modelMapper.map(order.getCar(), CarDTO.class));
        return orderDTO;
    }

    @Override
    public List<OrderDTO> allOrdersByMechanic(UUID id) {
        return this.orderRepository.findAllByResponsibleMechanic_IdAndStatusNotOrderByDateAsc(id, OrdersStatusEnum.FINISHED)
                .stream()
                .map(this::mapOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getUnassignedOrders() {
        return this.orderRepository.findAllByResponsibleMechanicIsNullOrderByDateAsc()
                .stream()
                .map(this::mapOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void assignOrder(EditOrderDTO editOrderDTO) {
        Order order = this.findOrderById(editOrderDTO.getId());
        order.setResponsibleMechanic(userService.findById(editOrderDTO.getMechanicId()).orElseThrow(null));
        this.orderRepository.save(order);
    }

    @Override
    public void removeOrder(UUID id) {
        Order order = this.findOrderById(id);
        if (!order.getStatus().equals(OrdersStatusEnum.IN_PROGRESS)) {
            this.orderRepository.delete(order);
        }
    }

    @Override
    public OrderDTO getOrderById(UUID id) {
        return this.mapOrderDTO(this.findOrderById(id));
    }

    @Override
    public void updateOrderStatusProgress(UUID id) {
        Order order = this.findOrderById(id);
        if (order.getStatus().equals(OrdersStatusEnum.PENDING) || order.getStatus().equals(OrdersStatusEnum.SCHEDULED)) {
            order.setStatus(OrdersStatusEnum.IN_PROGRESS);
        }
        this.orderRepository.save(order);
    }

    @Override
    public void addPartToOrder(UUID id, Long partId, int quantity) {
        Order order = this.findOrderById(id);
        for (int i = 0; i < quantity; i++) {
            order.getUsedParts().add(usedPartService.mapPartToUsedPart(partId, order));
        }
        this.orderRepository.save(order);
    }

    @Override
    public List<UsedPartDTO> getPartsForOrder(UUID id) {
        return usedPartService.getUsedPartsForOrder(id);
    }

    @Override
    public List<ServiceDTO> getServicesForOrder(UUID id) {
        return this.orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found")).getServices()
                .stream()
                .map(this.serviceService::map)
                .collect(Collectors.toList());
    }

    @Override
    public double calculatePartsSumForOrder(UUID id) {
        return usedPartService.getUsedPartsForOrder(id).stream()
                .mapToDouble(UsedPartDTO::getPrice)
                .sum();
    }

    @Override
    public double calculateServicesSumForOrder(UUID id) throws OrderNotFoundException {
        Order order = this.findOrderById(id);
        return order.getServices()
                .stream()
                .mapToDouble(ServiceEntity::getPrice)
                .sum();
    }

    @Override
    public double calculateTotalSumForOrder(UUID id) {
        double totalSum = this.calculatePartsSumForOrder(id) + this.calculateServicesSumForOrder(id);
        Order order = this.findOrderById(id);
        order.setTotalCost(totalSum);
        this.orderRepository.save(order);
        return totalSum;
    }

    @Override
    public void finishTask(UUID id, String mechanicComment) {
        Order order = this.findOrderById(id);
        if (mechanicComment == null || mechanicComment.isEmpty()) {
            mechanicComment = "Job Done!";
        }
        order.setMechanicComment(mechanicComment);
        order.setStatus(OrdersStatusEnum.FINISHED);
        sendConfirmationOrderDone(order.getAddedBy().getEmail(), order.getMechanicComment());
        this.orderRepository.save(order);

    }

    public void updateOrderStatus() {
        this.orderRepository.findAllByDateAndStatus(LocalDate.now(), OrdersStatusEnum.SCHEDULED)
                .forEach(order -> {
                    order.setStatus(OrdersStatusEnum.PENDING);
                    this.orderRepository.save(order);
                });
    }

    @Override
    public void addService(UUID id, UUID serviceId) {
        Order order = this.findOrderById(id);
        order.getServices().add(this.serviceService.getServiceEntity(serviceId));

        this.orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> allOrders() {
        return this.orderRepository.findAllByOrderByDateAsc()
                .stream()
                .map(this::mapOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteOldFinishedOrders() {
        LocalDate threeYearsAgo = LocalDate.now().minusYears(3);
        orderRepository.deleteByDateBefore(threeYearsAgo);

    }

    private void sendConfirmationOrderSubmit(String email, String carModel, String carMake, String carRegNumber) {
        mailService.sendMail(email, "Order Confirmation",
                "Thank you for made an order for your " + carMake + " " + carModel + " with registration number " + carRegNumber + ". We will contact you soon!");
    }

    private void sendConfirmationOrderDone(String email, String comment) {
        mailService.sendMail(email, "Order Finished",
                "Your order is now finished. Thank you for choosing Car Service! Our mechanic comments about all services done: " + comment);
    }


    private Order findOrderById(UUID id) {
       return this.orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }
}
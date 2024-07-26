package project.carservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.*;
import project.carservice.model.entity.Order;
import project.carservice.model.entity.enums.OrdersStatusEnum;
import project.carservice.repository.OrderRepository;
import project.carservice.repository.UserRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CarService carService;
    private final MailSender mailSender;
    private final PartService partService;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ModelMapper modelMapper, UserService userService, CarService carService, MailSender mailSender, PartService partService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.carService = carService;
        this.mailSender = mailSender;
        this.partService = partService;
    }

    @Override
    public void addOrder(AddOrderDTO addOrderDTO) {
        Order order = this.mapOrder(addOrderDTO);
        order.setStatus(OrdersStatusEnum.SCHEDULED);
        this.sendConfirmationOrderEmail(userService.getCurrentUser().getEmail());
        this.orderRepository.save(order);

    }

    private Order mapOrder(AddOrderDTO addOrderDTO) {
        Order order = modelMapper.map(addOrderDTO, Order.class);
        order.setCar(carService.getById(addOrderDTO.getId()));
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
        Order order = this.orderRepository.findById(editOrderDTO.getId()).orElse(null);
        order.setResponsibleMechanic(userRepository.findById(editOrderDTO.getMechanicId()).orElseThrow(null));
        this.orderRepository.save(order);
    }

    @Override
    public void removeOrder(UUID id) {
        Order order = this.orderRepository.findById(id).orElse(null);
        if (!order.getStatus().equals(OrdersStatusEnum.IN_PROGRESS)) {
            this.orderRepository.delete(order);
        }
    }

    @Override
    public OrderDTO getOrderById(UUID id) {
        return this.mapOrderDTO(this.orderRepository.findById(id).orElse(null));
    }

    @Override
    public void updateOrderStatusProgress(UUID id) {
        Order order = this.orderRepository.findById(id).orElse(null);
        if (order.getStatus().equals(OrdersStatusEnum.PENDING) || order.getStatus().equals(OrdersStatusEnum.SCHEDULED)) {
            order.setStatus(OrdersStatusEnum.IN_PROGRESS);
        }
        this.orderRepository.save(order);
    }

    @Override
    public void addPartToOrder(UUID id, Long partId, int quantity) {
        Order order = this.orderRepository.findById(id).orElse(null);
        for (int i = 0; i < quantity; i++) {
            order.getPartId().add(partId);
        }
        this.orderRepository.save(order);
    }

    @Override
    public List<PartDTO> getPartsForOrder(UUID id) {
        List<Long> partIds = this.orderRepository.findById(id).orElse(null).getPartId();
        return partIds.stream()
                .map(this.partService::getPartDetails)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateOrderPrice(UUID id) {
        Order order = this.orderRepository.findById(id).orElse(null);
        List<Long> partIds = order.getPartId();
        return partIds.stream()
                .map(this.partService::getPartDetails)
                .mapToDouble(PartDTO::getPrice)
                .sum();
    }

    @Override
    public void finishTask(UUID id, String mechanicComment) {
        Order order = this.orderRepository.findById(id).orElse(null);
        if (mechanicComment == null || mechanicComment.isEmpty()) {
            mechanicComment = "Job Done!";
        }
        order.setMechanicComment(mechanicComment);
        order.setStatus(OrdersStatusEnum.FINISHED);
        this.orderRepository.save(order);

    }

    @Override
    public void updateOrderStatusProgress() {
        this.orderRepository.findAllByDateAndStatus(LocalDate.now(), OrdersStatusEnum.SCHEDULED)
                .forEach(order -> {
                    order.setStatus(OrdersStatusEnum.PENDING);
                    this.orderRepository.save(order);
                });
    }

    private void sendConfirmationOrderEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Service Order Confirmation");
        message.setText("Thank you for making new Service Order in Car Service!");
        mailSender.send(message);
    }
}
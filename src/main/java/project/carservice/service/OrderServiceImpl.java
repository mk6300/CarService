package project.carservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.AddOrderDTO;
import project.carservice.model.dto.CarDTO;
import project.carservice.model.dto.EditOrderDTO;
import project.carservice.model.dto.OrderDTO;
import project.carservice.model.entity.Order;
import project.carservice.model.entity.enums.OrdersStatusEnum;
import project.carservice.repository.OrderRepository;
import project.carservice.repository.UserRepository;

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

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ModelMapper modelMapper, UserService userService, CarService carService, MailSender mailSender) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.carService = carService;
        this.mailSender = mailSender;
    }

    @Override
    public void addOrder(AddOrderDTO addOrderDTO) {
        Order order = this.mapOrder(addOrderDTO);
        order.setStatus(OrdersStatusEnum.SCHEDULED);
//        this.sendConfirmationOrderEmail(userService.getCurrentUser().getEmail());
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
        return this.orderRepository.findAllByAddedBy_Id(id)
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
        return this.orderRepository.findAllByResponsibleMechanic_IdAndStatusNot(id, OrdersStatusEnum.FINISHED)
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

    private void sendConfirmationOrderEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Service Order Confirmation");
        message.setText("Thank you for making new Service Order in Car Service!");
        mailSender.send(message);
    }
}
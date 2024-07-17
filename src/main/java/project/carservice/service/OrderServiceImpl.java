package project.carservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.AddOrderDTO;
import project.carservice.model.entity.Order;
import project.carservice.repository.OrderRepository;
@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    private final CarService carService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserService userService, CarService carService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.carService = carService;
    }

    @Override
    public void addOrder(AddOrderDTO addOrderDTO) {
        Order order = orderRepository.save(this.mapOrder(addOrderDTO));
        order.setCar(carService.getById(addOrderDTO.getId()));
        this.orderRepository.save(order);

    }

    private Order mapOrder(AddOrderDTO addOrderDTO) {
        Order order = modelMapper.map(addOrderDTO, Order.class);

        order.setAddedBy(userService.getCurrentUser());
        return order;
    }
}

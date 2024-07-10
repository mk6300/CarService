package project.carservice.service;

import org.modelmapper.ModelMapper;
import project.carservice.model.dto.AddOrderDTO;
import project.carservice.repository.OrderRepository;

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    private final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserService userService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public void addOrder(AddOrderDTO addOrderDTO) {

    }
}

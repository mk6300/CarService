package project.carservice.srevice.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import project.carservice.model.dto.OrderDTO;
import project.carservice.model.dto.ServiceDTO;
import project.carservice.model.dto.UsedPartDTO;
import project.carservice.model.dto.addDTO.AddOrderDTO;
import project.carservice.model.dto.editDTO.EditOrderDTO;
import project.carservice.model.entity.*;
import project.carservice.model.entity.enums.EngineTypeEnum;
import project.carservice.model.entity.enums.OrdersStatusEnum;
import project.carservice.repository.OrderRepository;
import project.carservice.service.*;
import project.carservice.service.impl.OrderServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

    @Mock
    private CarService carService;

    @Mock
    private PartService partService;

    @Mock
    private ServiceService serviceService;

    @Mock
    private MailService mailService;

    @Mock
    private UsedPartService usedPartService;
    private OrderServiceImpl orderService;

    Order order = new Order();

    AddOrderDTO addOrderDTO = new AddOrderDTO();

    User user = new User();

    Car car = new Car();

    OrderDTO orderDTO = new OrderDTO();

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl(orderRepository, modelMapper, userService, carService, partService, serviceService, usedPartService, mailService);
        order.setId(UUID.randomUUID());
        order.setDescription("Test Order");
        order.setStatus(OrdersStatusEnum.SCHEDULED);
        order.setDate(LocalDate.now());
        order.setUsedParts(new ArrayList<>());

        addOrderDTO.setCarId(UUID.randomUUID());
        addOrderDTO.setDescription("Test Order");
        addOrderDTO.setDate(LocalDate.now());

        user.setId(UUID.randomUUID());
        user.setUsername("testuser");

        car.setId(UUID.randomUUID());
        car.setMake("Volkswagen");
        car.setModel("Golf");
        car.setYear(2010);
        car.setEngine(EngineTypeEnum.PETROL);

        orderDTO.setId(UUID.randomUUID());
        orderDTO.setDescription("Test Order");
        orderDTO.setDate(LocalDate.now());
    }

    @Test
    public void testAddOrder() {
        when(modelMapper.map(addOrderDTO, Order.class)).thenReturn(order);
        when(userService.getCurrentUser()).thenReturn(user);
        when(carService.getById(addOrderDTO.getCarId())).thenReturn(car);

        orderService.addOrder(addOrderDTO);

        Assertions.assertEquals(OrdersStatusEnum.SCHEDULED, order.getStatus());
        Assertions.assertEquals(user, order.getAddedBy());
        Assertions.assertEquals(car, order.getCar());

        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testAllOrdersByUser() {
        when(orderRepository.findAllByAddedBy_IdAndStatusIsNotOrderByDateAsc(user.getId(), OrdersStatusEnum.FINISHED)).thenReturn(List.of(order));
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> orders = orderService.allOrdersByUser(user.getId());

        Assertions.assertEquals(1, orders.size());
        Assertions.assertEquals(OrdersStatusEnum.SCHEDULED.getDisplayName(), orders.get(0).getStatus());
        Assertions.assertEquals("Test Order", orders.get(0).getDescription());
        Assertions.assertEquals(LocalDate.now(), orders.get(0).getDate());
    }

    @Test
    public void testAllOrdersByUserFinished() {
        order.setStatus(OrdersStatusEnum.FINISHED);
        orderDTO.setStatus(OrdersStatusEnum.FINISHED.getDisplayName());
        when(orderRepository.findAllByAddedBy_IdAndStatusIsOrderByDateAsc(user.getId(), OrdersStatusEnum.FINISHED)).thenReturn(List.of(order));
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> orders = orderService.allOrdersByUserFinished(user.getId());

        Assertions.assertEquals(1, orders.size());
        Assertions.assertEquals(OrdersStatusEnum.FINISHED.getDisplayName(), orders.get(0).getStatus());
        Assertions.assertEquals("Test Order", orders.get(0).getDescription());
        Assertions.assertEquals(LocalDate.now(), orders.get(0).getDate());
    }

    @Test
    public void testAllOrdersByMechanic() {
        when(orderRepository.findAllByResponsibleMechanic_IdAndStatusNotOrderByDateAsc(user.getId(), OrdersStatusEnum.FINISHED)).thenReturn(List.of(order));
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> orders = orderService.allOrdersByMechanic(user.getId());

        Assertions.assertEquals(1, orders.size());
        Assertions.assertEquals(OrdersStatusEnum.SCHEDULED.getDisplayName(), orders.get(0).getStatus());
        Assertions.assertEquals("Test Order", orders.get(0).getDescription());
        Assertions.assertEquals(LocalDate.now(), orders.get(0).getDate());
    }

    @Test
    public void testGetUnassignedOrders() {
        when(orderRepository.findAllByResponsibleMechanicIsNullOrderByDateAsc()).thenReturn(List.of(order));
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> orders = orderService.getUnassignedOrders();

        Assertions.assertEquals(1, orders.size());
        Assertions.assertEquals(OrdersStatusEnum.SCHEDULED.getDisplayName(), orders.get(0).getStatus());
        Assertions.assertEquals("Test Order", orders.get(0).getDescription());
        Assertions.assertEquals(LocalDate.now(), orders.get(0).getDate());
    }

    @Test
    public void testAssignOrder() {
        EditOrderDTO editOrderDTO = new EditOrderDTO();
        editOrderDTO.setId(UUID.randomUUID());
        editOrderDTO.setMechanicId(UUID.randomUUID());

        when(orderRepository.findById(editOrderDTO.getId())).thenReturn(Optional.ofNullable(order));
        when(userService.findById(editOrderDTO.getMechanicId())).thenReturn(Optional.ofNullable(user));

        orderService.assignOrder(editOrderDTO);

        Assertions.assertEquals(user, order.getResponsibleMechanic());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testRemoveOrder() {
        order.setStatus(OrdersStatusEnum.IN_PROGRESS);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));

        orderService.removeOrder(order.getId());

        verify(orderRepository, times(0)).delete(order);
    }

    @Test
    public void testRemoveOrderWhenStatusIsNotInProgress() {
        order.setStatus(OrdersStatusEnum.SCHEDULED);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));

        orderService.removeOrder(order.getId());

        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    public void testGetOrderById() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        OrderDTO foundOrder = orderService.getOrderById(order.getId());

        Assertions.assertNotNull(foundOrder);
        Assertions.assertEquals("Test Order", foundOrder.getDescription());
        Assertions.assertEquals(LocalDate.now(), foundOrder.getDate());
        Assertions.assertEquals(OrdersStatusEnum.SCHEDULED.getDisplayName(), foundOrder.getStatus());
    }

    @Test
    public void testUpdateOrderStatusProgress() {
        order.setStatus(OrdersStatusEnum.PENDING);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));

        orderService.updateOrderStatusProgress(order.getId());

        Assertions.assertEquals(OrdersStatusEnum.IN_PROGRESS, order.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testUpdateOrderStatusProgressWhenStatusIsScheduled() {
        order.setStatus(OrdersStatusEnum.SCHEDULED);
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));

        orderService.updateOrderStatusProgress(order.getId());

        Assertions.assertEquals(OrdersStatusEnum.IN_PROGRESS, order.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testAddPartToOrder() {
        Long partId = 1L;
        int quantity = 2;
        UsedPart usedPart = new UsedPart();
        usedPart.setId(UUID.randomUUID());



        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));
        when(usedPartService.mapPartToUsedPart(partId, order)).thenReturn(usedPart);

        orderService.addPartToOrder(order.getId(), partId, quantity);

        Assertions.assertEquals(2, order.getUsedParts().size());
        Assertions.assertEquals(usedPart, order.getUsedParts().get(0));
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testAddPartToOrderWhenOrderNotFound() {
        Long partId = 1L;
        int quantity = 2;

        when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> orderService.addPartToOrder(UUID.randomUUID(), partId, quantity));
    }

    @Test
    public void testGetOrderByIdWhenOrderNotFound() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> orderService.getOrderById(order.getId()));
    }

    @Test
    public void testGetPartsForOrder() {
       UUID usedPartId = UUID.randomUUID();
        UsedPartDTO usedPartDTO = new UsedPartDTO();
        usedPartDTO.setId(usedPartId);
        usedPartDTO.setPrice(10.0);
        UsedPart usedPart = new UsedPart();
        usedPart.setId(usedPartId);
        usedPart.setPrice(10.0);
        order.getUsedParts().add(usedPart);
        when(usedPartService.getUsedPartsForOrder(order.getId())).thenReturn(List.of(usedPartDTO));

        List<UsedPartDTO> parts = orderService.getPartsForOrder(order.getId());

        Assertions.assertEquals(1, parts.size());
        Assertions.assertEquals(usedPartDTO.getPrice(), parts.get(0).getPrice());
        Assertions.assertEquals(usedPartDTO, parts.get(0));
    }

    @Test
    public void testGetServiceForOrder() {
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(UUID.randomUUID());

        ServiceEntity serviceEntity = new ServiceEntity();
        order.setServices(List.of(serviceEntity));

        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));
        when(serviceService.map(serviceEntity)).thenReturn(serviceDTO);

        List<ServiceDTO> services = orderService.getServicesForOrder(order.getId());

        Assertions.assertEquals(1, services.size());
        Assertions.assertEquals(serviceDTO, services.get(0));
        Assertions.assertEquals(serviceDTO.getId(), services.get(0).getId());
    }

    @Test
    public void testGetServiceForOrderWhenOrderNotFound() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(RuntimeException.class, () -> orderService.getServicesForOrder(order.getId()));
    }

    @Test
    public void testCalculatePartsSumForOrder() {
       UUID usedPartId = UUID.randomUUID();
        UsedPartDTO usedPartDTO = new UsedPartDTO();
        usedPartDTO.setId(usedPartId);
        usedPartDTO.setPrice(10.0);

        when(usedPartService.getUsedPartsForOrder(order.getId())).thenReturn(List.of(usedPartDTO));

        double sum = orderService.calculatePartsSumForOrder(order.getId());

        Assertions.assertEquals(10.0, sum);
    }

     @Test
    public void testCalculateServicesSumForOrder() {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setPrice(10.0);
        order.setServices(List.of(serviceEntity));
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));

        double sum = orderService.calculateServicesSumForOrder(order.getId());

        Assertions.assertEquals(10.0, sum);
    }

    @Test
    public void testCalculateTotalSumForOrder() {
        UsedPartDTO usedPartDTO = new UsedPartDTO();
        usedPartDTO.setPrice(10.0);
        usedPartDTO.setName("Test Part");
        usedPartDTO.setPrice(10.0);
        UsedPart usedPart = new UsedPart();
        usedPart.setId(UUID.randomUUID());
        usedPart.setName("Test Part");
        usedPart.setPrice(10.0);
        usedPart.setOrder(order);
        order.setUsedParts(List.of(usedPart));
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setName("Test Service");
        serviceEntity.setId(UUID.randomUUID());
        serviceEntity.setPrice(10.0);
        order.setServices(List.of(serviceEntity));
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));
        when(usedPartService.getUsedPartsForOrder(order.getId())).thenReturn(List.of(usedPartDTO));
        double sum = orderService.calculateTotalSumForOrder(order.getId());

        Assertions.assertEquals(20.0, sum);
    }

    @Test
    public void testUpdateOrderStatusToPending() {
        order.setStatus(OrdersStatusEnum.SCHEDULED);
        when(orderRepository.findAllByDateAndStatus(LocalDate.now(), OrdersStatusEnum.SCHEDULED)).thenReturn(List.of(order));

        orderService.updateOrderStatus();

        Assertions.assertEquals(OrdersStatusEnum.PENDING, order.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testAddServiceToOrder() {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(UUID.randomUUID());
        order.setServices(new ArrayList<>());
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));
        when(serviceService.getServiceEntity(serviceEntity.getId())).thenReturn(serviceEntity);

        orderService.addService(order.getId(), serviceEntity.getId());

        Assertions.assertEquals(1, order.getServices().size());
        Assertions.assertEquals(serviceEntity, order.getServices().get(0));
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testAllOrders () {
        when(orderRepository.findAllByOrderByDateAsc()).thenReturn(List.of(order));
        when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

        List<OrderDTO> orders = orderService.allOrders();

        Assertions.assertEquals(1, orders.size());
        Assertions.assertEquals(OrdersStatusEnum.SCHEDULED.getDisplayName(), orders.get(0).getStatus());
        Assertions.assertEquals("Test Order", orders.get(0).getDescription());
        Assertions.assertEquals(LocalDate.now(), orders.get(0).getDate());
    }
}
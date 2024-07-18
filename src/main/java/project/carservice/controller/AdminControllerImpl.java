package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import project.carservice.model.dto.AddOrderDTO;
import project.carservice.model.dto.EditOrderDTO;
import project.carservice.model.dto.OrderDTO;
import project.carservice.service.OrderService;
import project.carservice.service.UserService;

@Controller
public class AdminControllerImpl implements AdminController {

    private final OrderService orderService;
    private final UserService userService;

    public AdminControllerImpl(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public String getUnsignedOrders(Model model) {
        model.addAttribute("unsignedOrders", orderService.getUnassignedOrders());
        model.addAttribute("mechanics", userService.AllMechanics());
        model.addAttribute("editOrderDTO", new EditOrderDTO());

        return "unsigned-orders";
    }

    @Override
    public String assignOrder(OrderDTO OrderDTO) {
        return null;
    }

    @Override
    public String supplier(Model model) {
        return "supplier";
    }

}

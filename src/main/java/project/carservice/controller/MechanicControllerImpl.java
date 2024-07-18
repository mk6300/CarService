package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import project.carservice.service.OrderService;
import project.carservice.service.UserHelperService;

@Controller
public class MechanicControllerImpl implements MechanicController{

private final OrderService orderService;
private final UserHelperService userHelperService;

    public MechanicControllerImpl(OrderService orderService, UserHelperService userHelperService) {
        this.orderService = orderService;
        this.userHelperService = userHelperService;
    }

    @Override
    public String getMechanicTasks(Model model) {
        model.addAttribute("orders", orderService.allOrdersByMechanic(userHelperService.getUser().getId()));
        return "tasks";
    }
}

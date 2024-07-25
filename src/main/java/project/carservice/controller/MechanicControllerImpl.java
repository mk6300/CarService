package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import project.carservice.model.entity.enums.OrdersStatusEnum;
import project.carservice.service.OrderService;
import project.carservice.service.PartService;
import project.carservice.service.UserHelperService;

import java.util.UUID;

@Controller
public class MechanicControllerImpl implements MechanicController{

private final OrderService orderService;
private final UserHelperService userHelperService;
private final PartService partService;

    public MechanicControllerImpl(OrderService orderService, UserHelperService userHelperService, PartService partService) {
        this.orderService = orderService;
        this.userHelperService = userHelperService;
        this.partService = partService;
    }

    @Override
    public String getMechanicTasks(Model model) {
        model.addAttribute("orders", orderService.allOrdersByMechanic(userHelperService.getUser().getId()));
        return "tasks";
    }

    @Override
    public String getTaskById(Model model, UUID id) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("parts", partService.getAllParts());
        return "task";
    }

    @Override
    public String updateTaskProgress(UUID id) {
        orderService.updateOrderStatus(id);
        return "redirect:/mechanic/tasks";
    }
}

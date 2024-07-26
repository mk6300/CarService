package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.PartToOrderDTO;
import project.carservice.service.OrderService;
import project.carservice.service.PartService;
import project.carservice.service.UserService;

import java.util.UUID;

@Controller
public class MechanicControllerImpl implements MechanicController {

    private final OrderService orderService;
    private final UserService userService;
    private final PartService partService;

    public MechanicControllerImpl(OrderService orderService, UserService userService, PartService partService) {
        this.orderService = orderService;
        this.userService = userService;
        this.partService = partService;
    }

    @Override
    public String getMechanicTasks(Model model) {
        model.addAttribute("orders", orderService.allOrdersByMechanic(userService.getCurrentUser().getId()));
        return "tasks";
    }

    @Override
    public String getTaskById(Model model, UUID id) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("parts", partService.getAllParts());
        if (!model.containsAttribute("partToOrderDTO")) {
            model.addAttribute("partToOrderDTO", new PartToOrderDTO());
        }
        model.addAttribute("usedSpares", orderService.getPartsForOrder(id));
        model.addAttribute("orderPrice", orderService.calculateOrderPrice(id));
        return "task";
    }

    @Override
    public String updateTaskProgress(UUID id) {
        orderService.updateOrderStatusProgress(id);
        return "redirect:/mechanic/tasks";
    }

    @Override
    public String addSparePart(UUID id, PartToOrderDTO partToOrderDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("partToOrderDTO", partToOrderDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.partToOrderDTO", result);
            return "redirect:/mechanic/tasks/" + id;
        }
        orderService.addPartToOrder(id, partToOrderDTO.getSelectedPartId(), partToOrderDTO.getQuantity());
        return "redirect:/mechanic/tasks/" + id;
    }
    @Override
    public String finishTask(UUID id, String mechanicComment) {

        orderService.finishTask(id, mechanicComment);
        return "redirect:/mechanic/tasks";
    }
}
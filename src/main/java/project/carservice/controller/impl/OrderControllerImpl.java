package project.carservice.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.controller.OrderController;
import project.carservice.model.dto.addDTO.AddOrderDTO;
import project.carservice.model.dto.CarDTO;
import project.carservice.model.dto.OrderDTO;
import project.carservice.service.CarService;
import project.carservice.service.OrderService;
import project.carservice.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final CarService carService;
    private final UserService userService;

    public OrderControllerImpl(OrderService orderService, CarService carService, UserService userService) {
        this.orderService = orderService;
        this.carService = carService;
        this.userService = userService;
    }

    @Override
    public String orders(Model model) {
        List<OrderDTO> myOrders = orderService.allOrdersByUser(userService.getCurrentUser().getId());
        model.addAttribute("myOrders", myOrders);
        return "orders";
    }

    @Override
    public String addOrder(Model model) {

        if (!model.containsAttribute("addOrderDTO")) {
            model.addAttribute("addOrderDTO", AddOrderDTO.empty());
        }
        List<CarDTO> cars = carService.allOwnedBy(userService.getCurrentUser().getUsername());
        model.addAttribute("cars", cars);
        return "add-order";
    }

    @Override
    public String add (AddOrderDTO addOrderDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addOrderDTO", addOrderDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addOrderDTO", result);
            return "redirect:/orders/add-order";
        }

        this.orderService.addOrder(addOrderDTO);
        return "redirect:/orders/my-orders";
    }

    @Override
    public String removeOrder(UUID id) {
        orderService.removeOrder(id);
        return "redirect:/orders/my-orders";
    }

    @Override
    public String history(Model model) {
        List<OrderDTO> orders = orderService.allOrdersByUserFinished(userService.getCurrentUser().getId());
        model.addAttribute("orders", orders);
        return "history";
    }
    @Override
    public String orderInfo(Model model, UUID id) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("parts", orderService.getPartsForOrder(id));
        model.addAttribute("orderPartsPrice", orderService.calculatePartsSumForOrder(id));
        model.addAttribute("services", orderService.getServicesForOrder(id));
        model.addAttribute("orderServicePrice", orderService.calculateServicesSumForOrder(id));
        model.addAttribute("totalPrice", orderService.calculateTotalSumForOrder(id));
        return "order-details";
    }

    @Override
    public String allOrders(Model model) {
        model.addAttribute("orders", orderService.allOrders());
        return "all-orders";
    }

    @Override
    public String allOrdersInfo(Model model, UUID id) {
        return "order-details";
    }
}

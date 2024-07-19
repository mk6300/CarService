package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.AddDTOs.AddOrderDTO;
import project.carservice.model.dto.CarDTO;
import project.carservice.model.dto.OrderDTO;
import project.carservice.service.CarService;
import project.carservice.service.OrderService;
import project.carservice.service.UserHelperService;

import java.util.List;

@Controller
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final CarService carService;
    private final UserHelperService userHelperService;

    public OrderControllerImpl(OrderService orderService, CarService carService, UserHelperService userHelperService) {
        this.orderService = orderService;
        this.carService = carService;
        this.userHelperService = userHelperService;
    }

    @Override
    public String orders(Model model) {
        List<OrderDTO> myOrders = orderService.allOrdersByUser(userHelperService.getUser().getId());
        model.addAttribute("myOrders", myOrders);
        return "orders";
    }

    @Override
    public String addOrder(Model model) {

        if (!model.containsAttribute("addOrderDTO")) {
            model.addAttribute("addOrderDTO", AddOrderDTO.empty());
        }
        List<CarDTO> cars = carService.allOwnedBy(userHelperService.getUser().getUsername());
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
}

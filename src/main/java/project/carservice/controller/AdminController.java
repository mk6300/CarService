package project.carservice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.carservice.model.dto.OrderDTO;

@RequestMapping("/admin")
public interface AdminController {

    @GetMapping ("/unsigned-orders")
    String getUnsignedOrders(Model model);

    @PutMapping ("/assign-order")
    String assignOrder(OrderDTO orderDTO);

    @GetMapping ("/supplier")
    String supplier(Model model);
}

package project.carservice.controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.EditOrderDTO;
import project.carservice.model.dto.OrderDTO;

@RequestMapping("/admin")
public interface AdminController {

    @GetMapping ("/unsigned-orders")
    String getUnsignedOrders(Model model);

    @PutMapping ("/assign-mechanic")
    String assignOrder(EditOrderDTO editOrderDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping ("/suppliers")
    String suppliers(Model model);

    @GetMapping("/manage-users")
    String manageUsers(Model model);

}

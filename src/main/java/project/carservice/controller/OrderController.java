package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.AddOrderDTO;

@RequestMapping(name = "/orders")
public interface OrderController {

    @GetMapping("/orders/my-orders")
    String orders(Model model);

    @GetMapping("/orders/add-order")
    String addOrder(Model model);

    @PostMapping("/orders/add-order")
    String add(@Valid AddOrderDTO addOrderDTO, BindingResult result, RedirectAttributes redirectAttributes);
}

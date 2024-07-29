package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.addDTO.AddOrderDTO;

import java.util.UUID;

@RequestMapping("/orders")
public interface OrderController {

    @GetMapping("/my-orders")
    String orders(Model model);

    @GetMapping("/add-order")
    String addOrder(Model model);

    @PostMapping("/add-order")
    String add(@Valid AddOrderDTO addOrderDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @DeleteMapping("/remove/{id}")
    String removeOrder(@PathVariable UUID id);

    @GetMapping ("/history")
    String history(Model model);

    @GetMapping("/history/{id}")
    String orderInfo(Model model, @PathVariable UUID id);
}

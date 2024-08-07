package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.addDTO.AddOrderDTO;
import project.carservice.service.exceptions.OrderNotFoundException;

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

    @GetMapping("/history")
    String history(Model model);

    @GetMapping("/history/{id}")
    String orderInfo(Model model, @PathVariable UUID id);

    @GetMapping("/all-orders")
    String allOrders(Model model);

    @GetMapping("/all-orders/{id}")
    String allOrdersInfo(Model model, @PathVariable UUID id);

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    ModelAndView handleOrderNotFoundException(OrderNotFoundException exception);
}


package project.carservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping(name = "/orders")
public interface OrderController {

    @GetMapping("/add-order")
    String addOrder();
}

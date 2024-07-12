package project.carservice.controller;

import org.springframework.stereotype.Controller;

@Controller
public class OrderControllerImpl implements OrderController {
    @Override
    public String addOrder() {
        return "add-order";
    }
}

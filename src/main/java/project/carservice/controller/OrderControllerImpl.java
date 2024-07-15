package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class OrderControllerImpl implements OrderController {
    @Override
    public String addOrder(Model model) {
        return "add-order";
    }
}

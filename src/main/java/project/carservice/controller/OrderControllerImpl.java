package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.AddCarDTO;
import project.carservice.model.dto.AddOrderDTO;

@Controller
public class OrderControllerImpl implements OrderController {
    @Override
    public String orders(Model model) {
        return "orders";
    }

    @Override
    public String addOrder(Model model) {
        return "add-order";
    }

    @Override
    public String add(AddOrderDTO addOrderDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        return null;
    }
}

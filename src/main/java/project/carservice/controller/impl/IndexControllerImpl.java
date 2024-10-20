package project.carservice.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import project.carservice.controller.IndexController;
import project.carservice.service.OrderService;
import project.carservice.service.UserService;

import java.time.LocalDate;

@Controller
public class IndexControllerImpl implements IndexController {

    private final OrderService orderService;

    private final UserService userService;

    public IndexControllerImpl(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }


    @Override
    public String index(Model model) {
        model.addAttribute("orders", orderService.allOrdersFinishedCount());
        model.addAttribute("mechanics", userService.AllMechanicsCount());
        model.addAttribute("users", userService.AllUsersCount());
        model.addAttribute("years",LocalDate.now().getYear()-2000);
        return "index";
    }

    @Override
    public String contact() {
        return "contact";
    }

    @Override
    public String about(Model model) {
        model.addAttribute("orders", orderService.allOrdersFinishedCount());
        model.addAttribute("mechanics", userService.AllMechanicsCount());
        model.addAttribute("users", userService.AllUsersCount());
        model.addAttribute("years",LocalDate.now().getYear()-2000);
        return "about";
    }

    @Override
    public String service() {
        return "service";
    }

    @Override
    public String login(Model model) {
        return "login";
    }

    @Override
    public String loginError() {return "login-error";
    }
}

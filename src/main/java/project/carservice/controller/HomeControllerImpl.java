package project.carservice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeControllerImpl implements HomeController{
    @Override
    public String index() {
        return "index";
    }

    @Override
    public String home(Model model) {
        return "redirect:/home";
    }

    @Override
    public String contact(Model model) {
        return "contact";
    }
}

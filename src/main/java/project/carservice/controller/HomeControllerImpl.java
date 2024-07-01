package project.carservice.controller;

import org.springframework.ui.Model;

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
    public String about(Model model) {
        return "/about";
    }
}

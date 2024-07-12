package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
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
    public String contact() {

        return "contact";
    }

    @Override
    public String about() {
        return "about";
    }
}

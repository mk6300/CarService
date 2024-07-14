package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
@Controller
public class HomeControllerImpl implements HomeController{
    @Override
    public String home(Model model) {
        return "home";
    }
}

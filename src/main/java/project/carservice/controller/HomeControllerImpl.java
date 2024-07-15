package project.carservice.controller;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import project.carservice.model.entity.User;

@Controller
public class HomeControllerImpl implements HomeController {
    private final UserDetailsService userDetailsService;

    public HomeControllerImpl(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String home(Model model) {
        return "home";
    }
    @Override
    public String welcome(Model model) {
        return "welcome";
    }
    @Override
    public String garage(Model model) {
        return "garage";
    }


}

package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.LoginDTO;

@Controller
public class UserControllerImpl implements UserController {


    @Override
    public String login(Model model) {
        return "login";
    }

    @Override
    public String register() {
        return "register";
    }
}
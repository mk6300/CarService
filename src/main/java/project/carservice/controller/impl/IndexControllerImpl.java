package project.carservice.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import project.carservice.controller.IndexController;

@Controller
public class IndexControllerImpl implements IndexController {
    @Override
    public String index() {
        return "index";
    }

    @Override
    public String contact() {
        return "contact";
    }

    @Override
    public String about() {
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
}

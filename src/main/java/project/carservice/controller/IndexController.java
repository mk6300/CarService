package project.carservice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public interface IndexController {

    @GetMapping
    String index(Model model);

    @GetMapping("/contact")
    String contact();

    @GetMapping("/about")
    String about(Model model);

    @GetMapping("/service")
    String service();

    @GetMapping("/login")
    String login(Model model);

    @GetMapping("/login-error")
    String loginError();
}
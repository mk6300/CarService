package project.carservice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(name = "/home")
public interface HomeController {
    @GetMapping("/home")
    String home(Model model);

    @GetMapping("/home/garage")
    String garage(Model model);

    @GetMapping("/home/my-profile")
    String profile(Model model);
}

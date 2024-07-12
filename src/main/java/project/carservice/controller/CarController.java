package project.carservice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping( "/cars")
public interface CarController {

    @GetMapping("/add")
    String add(Model model);
}

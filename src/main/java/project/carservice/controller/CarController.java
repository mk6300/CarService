package project.carservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(name = "/cars")
public interface CarController {

    @GetMapping("/add-car")
    String addCar();
}

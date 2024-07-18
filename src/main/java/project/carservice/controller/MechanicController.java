package project.carservice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mechanic")
public interface MechanicController {

@GetMapping("/tasks")
    String getMechanicTasks(Model model);
}

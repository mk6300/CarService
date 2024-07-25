package project.carservice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/mechanic")
public interface MechanicController {

@GetMapping("/tasks")
    String getMechanicTasks(Model model);

@GetMapping("/tasks/{id}")
    String getTaskById(Model model, @PathVariable UUID id);
@PostMapping("/progress/{id}")
    String updateTaskProgress(@PathVariable UUID id);
}

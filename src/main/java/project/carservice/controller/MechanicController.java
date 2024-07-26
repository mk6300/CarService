package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.PartToOrderDTO;

import java.util.UUID;

@RequestMapping("/mechanic")
public interface MechanicController {

    @GetMapping("/tasks")
    String getMechanicTasks(Model model);

    @GetMapping("/tasks/{id}")
    String getTaskById(Model model, @PathVariable UUID id);

    @PostMapping("/progress/{id}")
    String updateTaskProgress(@PathVariable UUID id);

    @PostMapping("/add-part/{id}")
    String addSparePart(@PathVariable UUID id, @Valid PartToOrderDTO partToOrderDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @PostMapping("finish/{id}")
    String finishTask(@PathVariable UUID id,
                      @RequestParam("mechanicComment") String mechanicComment);

}

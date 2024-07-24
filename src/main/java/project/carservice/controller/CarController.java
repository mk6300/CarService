package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.AddCarDTO;

import java.util.UUID;

@RequestMapping( "/cars")
public interface CarController {

    @GetMapping("/add")
    String add(Model model);

    @PostMapping ("/add")
    String addCar (@Valid AddCarDTO addPaintingDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @DeleteMapping("/remove/{id}")
    String removeCar(@PathVariable UUID id);

}

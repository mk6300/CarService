package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.addDTO.AddServiceDTO;
import project.carservice.model.dto.editDTO.EditServiceDTO;

import java.util.UUID;

@RequestMapping("/services")
public interface ServiceController {

    @GetMapping ("/manage-services")
    String manageServices(@RequestParam(required = false)UUID id, Model model);

    @PostMapping("/manage-services")
    String serviceInfo(@RequestParam UUID serviceId, Model model);

    @GetMapping("/add-service")
    String add(Model model);

    @PostMapping("/add-service")
    String addService(@Valid AddServiceDTO addServiceDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/edit/{id}")
    String edit(@PathVariable UUID id, Model model);

    @PostMapping("/edit")
    String editService(@Valid EditServiceDTO editServiceDTO, BindingResult result, RedirectAttributes redirectAttributes);



}

package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.editDTO.EditOrderDTO;
import project.carservice.model.dto.editDTO.EditUserDTO;


import java.util.UUID;

@RequestMapping("/admin")
public interface AdminController {

    @GetMapping ("/unsigned-orders")
    String getUnsignedOrders(Model model);

    @PutMapping ("/assign-mechanic")
    String assignOrder(EditOrderDTO editOrderDTO);


    @GetMapping("/manage-users")
    String manageUsers(Model model);

    @PostMapping("/manage")
    String manageUser(@RequestParam UUID id, @RequestParam String action, RedirectAttributes redirectAttributes);

    @GetMapping("/edit-user/{id}")
    String edit(@PathVariable UUID id, Model model);

    @PutMapping("/edit-user")
    String editUser(@Valid EditUserDTO editUserDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @PostMapping ("/remove-mechanic")
    String removeMechanic(@RequestParam UUID id);

    @PostMapping ("/remove-admin")
    String removeAdmin(@RequestParam UUID id);

    @DeleteMapping ("/remove-user/{id}")
    String removeUser(@PathVariable UUID id);

    @DeleteMapping ("/remove-order/{id}")
    String removeOrder(@PathVariable UUID id);

}

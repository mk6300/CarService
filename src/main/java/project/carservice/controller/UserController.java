package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.RegisterUserDTO;
import project.carservice.model.dto.editDTO.EditUserDTO;

import java.util.UUID;

@RequestMapping("/users")
public interface UserController {
    @GetMapping("/login")
    String login(Model model);

    @GetMapping("/register")
    String register(Model model);
    @PostMapping("/register")
    String registerConfirm(@Valid RegisterUserDTO registerUserDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/my-profile")
    String profile(Model model);

    @GetMapping("/garage")
    String garage(Model model, @AuthenticationPrincipal UserDetails userDetails);

    @GetMapping("/edit/{id}")
    String edit(@PathVariable UUID id, Model model);

    @PostMapping("/edit-profile")
    String editProfile(@Valid EditUserDTO editUserDTO, BindingResult result, RedirectAttributes redirectAttributes);

}

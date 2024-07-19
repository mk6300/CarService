package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.AddDTOs.RegisterUserDTO;

@RequestMapping("/users")
public interface UserController {
    @GetMapping("/login")
    String login(Model model);

    @GetMapping("/register")
    String register(Model model);
    @PostMapping("/register")
    String registerConfirm(@Valid RegisterUserDTO registerUserDTO, BindingResult result, RedirectAttributes redirectAttributes);
}

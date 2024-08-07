package project.carservice.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.controller.RegistrationController;
import project.carservice.model.dto.addDTO.RegisterUserDTO;
import project.carservice.service.UserService;

@Controller
public class RegistrationImpl implements RegistrationController {

    private final UserService userService;

    public RegistrationImpl(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO registerUserDTO() {
        return new RegisterUserDTO();
    }
    @Override
    public String register(Model model) {

        return "register";
    }

    @Override
    public String registerUser(RegisterUserDTO registerUserDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {
            result.addError(
                    new FieldError(
                            "differentConfirmPassword",
                            "confirmPassword",
                            "Passwords must be the same."));
        }

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("registerUserDTO", registerUserDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerUserDTO", result);

            return "redirect:/register";
        }

        this.userService.register(registerUserDTO);
        return "redirect:/";
    }
}

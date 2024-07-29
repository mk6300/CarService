package project.carservice.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.CarDTO;
import project.carservice.model.dto.RegisterUserDTO;
import project.carservice.model.dto.UserDTO;
import project.carservice.model.dto.editDTO.EditUserDTO;
import project.carservice.model.user.AppUserDetails;
import project.carservice.service.CarService;
import project.carservice.service.UserService;

import java.util.List;
import java.util.UUID;


@Controller
public class UserControllerImpl implements UserController {

private final UserService userService;
public final CarService carService;

    public UserControllerImpl(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    @ModelAttribute("registerUserDTO")
    public RegisterUserDTO registerUserDTO() {
        return new RegisterUserDTO();
    }

    @Override
    public String login(Model model) {
        return "login";
    }

    @Override
    public String register(Model model) {

        return "register";
    }

    @Override
    public String registerConfirm(RegisterUserDTO registerUserDTO, BindingResult result, RedirectAttributes redirectAttributes) {
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

            return "register";
        }

        this.userService.register(registerUserDTO);
        return "redirect:/";
    }

    @Override
    public String garage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof AppUserDetails appUserDetails) {

            List<CarDTO> myCars = carService.allOwnedBy(appUserDetails.getUsername());
            model.addAttribute("myCars", myCars);}

        return "garage";
    }

    @Override
    public String edit(UUID id, Model model) {
        EditUserDTO editUserDTO = userService.getUserEditById(id);
        if (!model.containsAttribute("editUserDTO")) {
            model.addAttribute("editUserDTO", editUserDTO);
        }

        return "edit-profile";
    }

    @Override
    public String editProfile(EditUserDTO editUserDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("editUserDTO", editUserDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.editUserDTO", result);

            return "redirect:/users/edit/" + editUserDTO.getId();
        }
        userService.editUser(editUserDTO);
        return "redirect:/users/my-profile";
    }


    @Override
    public String profile (Model model){
        model.addAttribute("user",userService.getUserById(userService.getCurrentUser().getId()));
        model.addAttribute("userDTO", new UserDTO());

        return "my-profile";
    }

}
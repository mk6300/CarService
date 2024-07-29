package project.carservice.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import project.carservice.model.user.AppUserDetails;

@Controller
public class HomeControllerImpl implements HomeController {


    @Override
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof AppUserDetails appUserDetails) {
            model.addAttribute("welcomeMessage", appUserDetails.getFullName());
        } else {
            model.addAttribute("welcomeMessage", "Anonymous");
        }

        return "home";
    }


}

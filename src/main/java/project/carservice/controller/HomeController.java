package project.carservice.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.carservice.model.user.AppUserDetails;

@RequestMapping("/home")
public interface HomeController {
    @GetMapping()
    String home(Model model, @AuthenticationPrincipal UserDetails userDetails);


}

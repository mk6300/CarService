package project.carservice.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.carservice.model.user.AppUserDetails;

@RequestMapping(name = "/home")
public interface HomeController {
    @GetMapping("/home")
    String home(Model model, @AuthenticationPrincipal UserDetails userDetails);

    @GetMapping("/home/garage")
    String garage(Model model, @AuthenticationPrincipal UserDetails userDetails);

    @GetMapping("/home/my-profile")
    String profile(Model model);
}

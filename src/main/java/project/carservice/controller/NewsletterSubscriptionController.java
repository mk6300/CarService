package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.SubscriberDTO;

public interface NewsletterSubscriptionController {

    @PostMapping("/subscribe")
    String subscribe(@Valid @ModelAttribute("subscriberDTO") SubscriberDTO subscriberDTO, BindingResult result, RedirectAttributes redirectAttributes, Model model);

    @PostMapping("/unsubscribe")
    String unsubscribe(@RequestParam("email") String email);
}

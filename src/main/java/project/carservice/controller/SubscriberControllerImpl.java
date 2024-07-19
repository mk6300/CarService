package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.AddDTOs.SubscriberDTO;
import project.carservice.service.SubscriberService;



@Controller
public class SubscriberControllerImpl implements SubscriberController {
    private final SubscriberService subscriberService;

    public SubscriberControllerImpl(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;

    }
    @Override
    public String subscribe(SubscriberDTO subscriberDTO, BindingResult result, RedirectAttributes redirectAttributes, Model model) {



        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("subscriberDTO", subscriberDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.subscriberDTO", result);
            return "redirect:/home";

        } else {
            subscriberService.subscribe(subscriberDTO);

            redirectAttributes.addFlashAttribute("message OK", "Subscription successful!");
            return "redirect:/home";
        }
    }
}
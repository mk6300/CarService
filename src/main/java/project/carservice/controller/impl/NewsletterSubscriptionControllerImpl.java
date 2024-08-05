package project.carservice.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.controller.NewsletterSubscriptionController;
import project.carservice.model.dto.SubscriberDTO;
import project.carservice.service.NewsletterService;



@Controller
public class NewsletterSubscriptionControllerImpl implements NewsletterSubscriptionController {
    private final NewsletterService newsletterService;

    public NewsletterSubscriptionControllerImpl(NewsletterService subscriberService) {
        this.newsletterService = subscriberService;

    }
    @Override
    public String subscribe(SubscriberDTO subscriberDTO, BindingResult result, RedirectAttributes redirectAttributes, Model model) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("subscriberDTO", subscriberDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.subscriberDTO", result);

        }
        else

        {newsletterService.subscribe(subscriberDTO);

            redirectAttributes.addFlashAttribute("message2", "Thank you for subscribing!");
        }
        return "redirect:/";
    }

    @Override
    public String unsubscribe(String email) {
        newsletterService.unsubscribe(email);
        return null;
    }
}
package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.AddPartDTO;

@Controller
public class PartsControllerImpl implements PartsController{
    @Override
    public String manageParts(Model model) {
        return "manage-parts";
    }

    @Override
    public String addPart(Model model) {
        return "add-part";
    }

    @Override
    public String addPart(AddPartDTO addPartDTO, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("addPartDTO", addPartDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPartDTO", result);

            return "redirect:/parts/add-part";
        }

        return null;
    }
}

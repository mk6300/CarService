package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.AddPartDTO;

@RequestMapping("/parts")
public interface PartsController {

    @GetMapping("/manage-parts")
    public String manageParts(Model model);

    @GetMapping("add-part")
    public String addPart(Model model);

    @PostMapping("/add-part")
    public String addPart(@Valid AddPartDTO addPartDTO, BindingResult result, RedirectAttributes redirectAttributes);
}

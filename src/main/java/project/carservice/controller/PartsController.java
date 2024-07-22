package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.AddPartDTO;

@RequestMapping("/parts")
public interface PartsController {

    @GetMapping("/manage-parts")
    public String manageParts(Model model, @RequestParam(required = false) Long id);
    @PostMapping("/manage-parts")
    public String selectPart(@RequestParam("partId") Long partId, Model model);

    @GetMapping("add-part")
    public String addPart(Model model);

    @PostMapping("/add-part")
    public String addPart(@Valid AddPartDTO addPartDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/{id}")
    public String editPart(@PathVariable("id") Long id, Model model);
}

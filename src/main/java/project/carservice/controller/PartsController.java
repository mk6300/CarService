package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.addDTO.AddPartDTO;

@RequestMapping("/parts")
public interface PartsController {

    @GetMapping("/manage-parts")
    String manageParts(Model model, @RequestParam(required = false) Long id);
    @PostMapping("/manage-parts")
    String selectPart(@RequestParam("partId") Long partId, Model model);

    @GetMapping("add-part")
    String add(Model model);

    @PostMapping("/add-part")
    String addPart(@Valid AddPartDTO addPartDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @GetMapping("/edit/{id}")
    String editPart(@PathVariable("id") Long id, Model model);

    @DeleteMapping("/remove/{id}")
    String removePart(@PathVariable("id") Long id);
}

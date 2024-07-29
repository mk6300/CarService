package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.addDTO.AddSupplierDTO;

@RequestMapping("/supplier")
public interface SupplierController {

    @GetMapping ("/add-supplier")
    String addSupplier(Model model);
    @PostMapping ("/add-supplier")
    String addSupplier(@Valid AddSupplierDTO addSupplierDTO, BindingResult result, RedirectAttributes redirectAttributes);
}

package project.carservice.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.SupplierDTO;
import project.carservice.model.dto.addDTO.AddSupplierDTO;

import java.util.UUID;

@RequestMapping("/supplier")
public interface SupplierController {

    @GetMapping ("/suppliers")
    String suppliers(Model model);

    @GetMapping ("/add-supplier")
    String addSupplier(Model model);
    @PostMapping ("/add-supplier")
    String addSupplier(@Valid AddSupplierDTO addSupplierDTO, BindingResult result, RedirectAttributes redirectAttributes);

    @PostMapping("/supplier-action")
    String handleSupplierAction(@RequestParam("id") UUID supplierId, @RequestParam("action") String action);

    @GetMapping("/edit-supplier/{id}")
    String editSupplier(@PathVariable("id") UUID id, Model model);

    @PostMapping("/edit-supplier")
    String edit(@Valid SupplierDTO supplierDTO, BindingResult result, RedirectAttributes redirectAttributes);

}

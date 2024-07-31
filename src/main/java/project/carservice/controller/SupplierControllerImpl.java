package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.SupplierDTO;
import project.carservice.model.dto.addDTO.AddSupplierDTO;
import project.carservice.service.SupplierService;

import java.util.UUID;


@Controller
public class SupplierControllerImpl implements SupplierController {

    private final SupplierService supplierService;

    public SupplierControllerImpl(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public String suppliers(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "suppliers";
    }

    @Override
    public String addSupplier(Model model) {

        if (!model.containsAttribute("addSupplierDTO")) {
            model.addAttribute("addSupplierDTO", AddSupplierDTO.empty());
        }
        return "add-supplier";
    }

    @Override
    public String addSupplier(AddSupplierDTO addSupplierDTO, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addSupplierDTO", addSupplierDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addSupplierDTO", result);

            return "redirect:/supplier/add-supplier";
        }

        this.supplierService.addSupplier(addSupplierDTO);
        return "redirect:/supplier/suppliers";
    }

    @Override
    public String handleSupplierAction(UUID supplierId, String action) {
        {
            if ("edit".equals(action)) {
                return "redirect:/supplier/edit-supplier/" + supplierId;
            } else if ("remove".equals(action)) {
                supplierService.removeSupplier(supplierId);

                return "redirect:/supplier/suppliers";
            }
            return "redirect:/supplier/suppliers";
        }
    }

    @Override
    public String editSupplier(UUID id, Model model) {
        SupplierDTO supplierDTO = supplierService.getSupplierById(id);
        if (!model.containsAttribute("supplierDTO")) {
            model.addAttribute("supplierDTO", supplierDTO);
        }
        return "edit-supplier";
    }

    @Override
    public String edit(SupplierDTO supplierDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("supplierDTO", supplierDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.supplierDTO", result);
            return "redirect:/supplier/edit-supplier/" + supplierDTO.getId();
        }

        this.supplierService.editSupplier(supplierDTO);
        return "redirect:/supplier/suppliers";
    }

}




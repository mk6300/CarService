package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.addDTO.AddSupplierDTO;
import project.carservice.service.SupplierService;


@Controller
public class SupplierControllerImpl implements SupplierController {

    private final SupplierService supplierService;

    public SupplierControllerImpl(SupplierService supplierService) {
        this.supplierService = supplierService;
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
        return "redirect:/admin/suppliers";
    }
}




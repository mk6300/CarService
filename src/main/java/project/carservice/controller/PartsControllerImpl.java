package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.addDTO.AddPartDTO;
import project.carservice.model.dto.PartDTO;
import project.carservice.model.dto.editDTO.EditPartDTO;
import project.carservice.service.PartService;
import project.carservice.service.SupplierService;

@Controller
public class PartsControllerImpl implements PartsController{

    private final PartService partService;
    private final SupplierService supplierService;

    public PartsControllerImpl(PartService partService, SupplierService supplierService) {
        this.partService = partService;
        this.supplierService = supplierService;
    }
    @Override
    public String manageParts(Model model, Long partId) {

        model.addAttribute("parts", partService.getAllParts());
        model.addAttribute("selectedPartId", partId);

        if (partId != null) {
            PartDTO selectedPart = partService.getPartDetails(partId);
            model.addAttribute("selectedPart", selectedPart);
            if (selectedPart != null) {
                String supplierName = supplierService.getSupplierById(selectedPart.getSupplierId()).getName();
                model.addAttribute("supplierName", supplierName);

            }
        }
            return "manage-parts";
    }
    @Override
    public String selectPart(@RequestParam("partId") Long partId, Model model) {

        return "redirect:/parts/manage-parts?partId=" + partId;
    }

    @Override
    public String add(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        if (!model.containsAttribute("addPartDTO")) {
            model.addAttribute("addPartDTO", new AddPartDTO());
        }
        return "add-part";
    }

    @Override
    public String addPart(AddPartDTO addPartDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addPartDTO", addPartDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addPartDTO", result);
            return "redirect:/parts/add-part";
        }

        this.partService.addPart(addPartDTO);
        return "redirect:/parts/manage-parts";
    }

    @Override
    public String edit(Long id, Model model) {

        if (!model.containsAttribute("partDTO")) {
            model.addAttribute("partDTO", partService.getPartDetails(id));
        }

        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        model.addAttribute("supplierName", supplierService.getSupplierById(partService.getPartDetails(id).getSupplierId()).getName());
        return "edit-part";
    }

    @Override
    public String editPart(PartDTO partDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("partDTO", partDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.partDTO", result);
            return "redirect:/parts/edit/" + partDTO.getId();
        }

        this.partService.editPart(partDTO.getId(), partDTO);
        return "redirect:/parts/manage-parts";

    }

    @Override
    public String removePart(Long id) {
        partService.deletePart(id);
        return "redirect:/parts/manage-parts";
    }
}

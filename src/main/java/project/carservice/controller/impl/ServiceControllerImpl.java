package project.carservice.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.controller.ServiceController;
import project.carservice.model.dto.ServiceDTO;
import project.carservice.model.dto.addDTO.AddServiceDTO;
import project.carservice.model.dto.editDTO.EditServiceDTO;
import project.carservice.service.ServiceService;

import java.util.UUID;

@Controller
public class ServiceControllerImpl implements ServiceController {

    private final ServiceService serviceService;

    public ServiceControllerImpl(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public String manageServices(UUID id, Model model) {
        model.addAttribute("services", serviceService.getAllServices());
        model.addAttribute("selectedServiceId", id);

        if (id != null) {
            ServiceDTO selected = serviceService.getServiceDetails(id);
            model.addAttribute("selectedService", selected);
        }
        return "manage-services";
    }

    @Override
    public String serviceInfo(UUID serviceId, Model model) {

        return "redirect:/services/manage-services?id=" + serviceId;
    }

    @Override
    public String add(Model model) {
        if (!model.containsAttribute("addServiceDTO")) {
            model.addAttribute("addServiceDTO", new AddServiceDTO());
        }
        return "add-service";
    }

    @Override
    public String addService(AddServiceDTO addServiceDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("addServiceDTO", addServiceDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addServiceDTO", result);
            return "redirect:/services/add-service";
        }
        serviceService.addService(addServiceDTO);
        return "redirect:/services/manage-services";
    }

    @Override
    public String edit(UUID id, Model model) {

        EditServiceDTO editServiceDTO = serviceService.editServiceDTO(id);
        if (!model.containsAttribute("editServiceDTO")) {
            model.addAttribute("editServiceDTO", editServiceDTO);
        }
        return "edit-service";
    }

    @Override
    public String editService(EditServiceDTO editServiceDTO, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("editServiceDTO", editServiceDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editServiceDTO", result);
            return "redirect:/services/edit/" + editServiceDTO.getId();
        }

        serviceService.editService(editServiceDTO);
        return "redirect:/services/manage-services";
    }
}

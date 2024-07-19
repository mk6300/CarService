package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.model.dto.AddDTOs.AddCarDTO;
import project.carservice.model.entity.enums.EngineTypeEnum;
import project.carservice.service.CarService;

@Controller
public class CarControllerImpl implements CarController {
    private final CarService carService;

    public CarControllerImpl(CarService carService) {
        this.carService = carService;
    }


    @Override
    public String add(Model model) {
        if (!model.containsAttribute("addCarDTO")) {
            model.addAttribute("addCarDTO", AddCarDTO.empty());
        }
        return "add-car";
    }

    @Override
    public String addCar(AddCarDTO addCarDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addCarDTO", addCarDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addCarDTO", result);
            return "redirect:/cars/add";
        }
    this.carService.addCar(addCarDTO);
        return "redirect:/home/garage";
    }


    @ModelAttribute("allEngineTypes")
    public EngineTypeEnum[] allEngineTypes() {
        return EngineTypeEnum.values();
    }
}
package project.carservice.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.controller.CarController;
import project.carservice.model.dto.addDTO.AddCarDTO;
import project.carservice.model.entity.enums.EngineTypeEnum;
import project.carservice.service.CarService;
import project.carservice.service.exceptions.CarNotFoundException;
import project.carservice.service.exceptions.OrderNotFoundException;


import java.util.UUID;

@Controller
public class CarControllerImpl implements CarController {
    private final CarService carService;

    public CarControllerImpl(CarService carService) {
        this.carService = carService;
    }


    @Override
    public String add(Model model) {

        model.addAttribute("allEngineTypes", EngineTypeEnum.values());

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
        return "redirect:/users/garage";
    }

    @Override
    public String removeCar(UUID id) {
        this.carService.removeCar(id);
        return "redirect:/users/garage";
    }

    @Override
    public ModelAndView handleCarNotFoundException(CarNotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("car-not-found");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }

}
package project.carservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.carservice.model.dto.AddCarDTO;
import project.carservice.model.entity.enums.EngineTypeEnum;

@Controller
public class CarControllerImpl implements CarController{

    @Override
    public String add(Model model) {
        if (!model.containsAttribute("addCarDTO")) {
            model.addAttribute("addCarDTO", AddCarDTO.empty());
        }

        return "add-car";
    }
    @ModelAttribute("allEngineTypes")
    public EngineTypeEnum[] allEngineTypes() {
        return EngineTypeEnum.values();
    }
}

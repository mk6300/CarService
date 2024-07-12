package project.carservice.controller;

import org.springframework.stereotype.Controller;

@Controller
public class CarControllerImpl implements CarController{

    @Override
    public String addCar() {
        return "add-car";
    }
}

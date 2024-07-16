package project.carservice.service;

import project.carservice.model.dto.AddCarDTO;
import project.carservice.model.dto.CarDTO;
import project.carservice.model.user.AppUserDetails;

import java.util.List;

public interface CarService {

    void addCar(AddCarDTO addCarDTO);

    List<CarDTO> allOwnedBy(AppUserDetails appUserDetails);

}

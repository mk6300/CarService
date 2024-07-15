package project.carservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.AddCarDTO;
import project.carservice.model.entity.Car;
import project.carservice.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;

    private final AppUserDetailsService appUserDetailsService;
    private final ModelMapper modelMapper;


    public CarServiceImpl(CarRepository carRepository, AppUserDetailsService appUserDetailsService, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.appUserDetailsService = appUserDetailsService;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addCar(AddCarDTO addCarDTO) {
        Car newCar = new Car();

    }
}

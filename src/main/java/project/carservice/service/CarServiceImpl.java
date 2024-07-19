package project.carservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.AddDTOs.AddCarDTO;
import project.carservice.model.dto.CarDTO;
import project.carservice.model.entity.Car;
import project.carservice.repository.CarRepository;
import project.carservice.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    public CarServiceImpl(CarRepository carRepository, UserRepository userRepository, ModelMapper modelMapper, UserService userService) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public void addCar(AddCarDTO addCarDTO) {
        this.carRepository.save(this.mapCar(addCarDTO));

    }

    @Override
    public List<CarDTO> allOwnedBy(String username) {
        return carRepository.getAllByOwner_Username(username)
                .stream()
                .map(this::mapCarDTO)
                .toList();
    }

    @Override
    public Car getById(UUID id) {
        return carRepository.findById(id).orElseThrow();
    }


    private Car mapCar(AddCarDTO addCarDTO) {
        Car car = modelMapper.map(addCarDTO, Car.class);

        car.setOwner(userService.getCurrentUser());
        return car;
    }

    private CarDTO mapCarDTO(Car car) {

        return modelMapper.map(car, CarDTO.class);
    }
}

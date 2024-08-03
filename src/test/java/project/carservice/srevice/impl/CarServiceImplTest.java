package project.carservice.srevice.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import project.carservice.model.dto.CarDTO;
import project.carservice.model.dto.addDTO.AddCarDTO;
import project.carservice.model.entity.Car;
import project.carservice.model.entity.User;
import project.carservice.model.entity.enums.EngineTypeEnum;
import project.carservice.repository.CarRepository;
import project.carservice.service.impl.CarServiceImpl;
import project.carservice.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
    @Mock
    private CarRepository carRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;


    private CarServiceImpl carService;

    private Car car;
    private AddCarDTO addCarDTO;
    private CarDTO carDTO;
    private User user;

    @BeforeEach
    void setUp() {
        carService = new CarServiceImpl(carRepository, modelMapper, userService);

        car = new Car();
        car.setId(UUID.randomUUID());
        car.setMake("Volkswagen");
        car.setModel("Golf");
        car.setYear(2010);
        car.setEngine(EngineTypeEnum.PETROL);
        car.setVinNumber("WVWZZZ1JZ1D442653");
        car.setRegistration("A1234AB");

        user = new User();
        user.setUsername("testuser");

        car.setOwner(user);

        addCarDTO = new AddCarDTO("A1234AB", "Volkswagen", "Golf", 2010, EngineTypeEnum.PETROL, "WVWZZZ1JZ1D442653");

        carDTO = new CarDTO();
        carDTO.setMake("Volkswagen");
        carDTO.setModel("Golf");
    }

    @Test
    void addCar_ShouldSaveCar() {
        when(modelMapper.map(addCarDTO, Car.class)).thenReturn(car);
        when(userService.getCurrentUser()).thenReturn(user);

        carService.addCar(addCarDTO);

        verify(carRepository, times(1)).save(car);
    }

    @Test
    void allOwnedBy_ShouldReturnListOfCarDTO() {
        when(carRepository.getAllByOwner_Username("testuser")).thenReturn(List.of(car));
        when(modelMapper.map(car, CarDTO.class)).thenReturn(carDTO);

        List<CarDTO> cars = carService.allOwnedBy("testuser");

        Assertions.assertEquals(1, cars.size());
        Assertions.assertEquals("Volkswagen", cars.get(0).getMake());
        Assertions.assertEquals("Golf", cars.get(0).getModel());
    }

    @Test
    void getById_ShouldReturnCar() {
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));

        Car foundCar = carService.getById(car.getId());

        Assertions.assertNotNull(foundCar);
        Assertions.assertEquals("Volkswagen", foundCar.getMake());
    }

    @Test
    void getById_ShouldThrowExceptionWhenCarNotFound() {
        when(carRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> carService.getById(UUID.randomUUID()));
    }

    @Test
    void removeCar_ShouldDeleteCar() {
        when(carRepository.findById(car.getId())).thenReturn(Optional.of(car));

        carService.removeCar(car.getId());

        verify(carRepository, times(1)).delete(car);
        Assertions.assertEquals(0, carRepository.count());
    }
}

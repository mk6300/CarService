package project.carservice.controller.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.validation.BindingResult;
import project.carservice.model.dto.addDTO.AddCarDTO;
import project.carservice.model.entity.Car;
import project.carservice.model.entity.User;
import project.carservice.model.entity.enums.EngineTypeEnum;
import project.carservice.repository.CarRepository;
import project.carservice.repository.UserRepository;

import java.util.UUID;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
public class CarControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        carRepository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        carRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Test
    public void testAddView() throws Exception {
        mockMvc.perform(get("/cars/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-car"))
                .andExpect(model().attributeExists("addCarDTO"));
    }

    @Test
    public void testRemoveCar() throws Exception {
        User testUser = new User();
        testUser.setUsername("Ivan9999");
        testUser.setPassword("12345");
        testUser.setFirstName("Ivan");
        testUser.setLastName("Ivanov");
        testUser.setEmail("mail@example.com");
        testUser.setPhone("0888888888");
        userRepository.save(testUser);
        Assertions.assertEquals(1, userRepository.count());

        Car testCar = new Car();
        testCar.setMake("BMW");
        testCar.setModel("X5");
        testCar.setYear(2020);
        testCar.setEngine(EngineTypeEnum.DIESEL);
        testCar.setOwner(testUser);
        testCar.setRegistration("A1234AA");
        testCar.setVinNumber("WVWZZZ1JZ1D234097");
        carRepository.save(testCar);
        Assertions.assertEquals(1, carRepository.count());

        mockMvc.perform(delete("/cars/remove/{id}", testCar.getId().toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/garage"));
        Assertions.assertEquals(0, carRepository.count());
    }

    @Test
    public void testAddCarInvalidData() throws Exception {

        BindingResult bindingResult = (BindingResult) mockMvc.perform(post("/cars/add")
                        .param("registration", "A")
                        .param("make", "BMW")
                        .param("model", "X5")
                        .param("year", "2020")
                        .param("engine", "DIESEL")
                        .param("vinNumber", "22323")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cars/add"))
                .andExpect(flash().attributeExists("addCarDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.addCarDTO"))
                .andReturn().getFlashMap().get("org.springframework.validation.BindingResult.addCarDTO");
        Assertions.assertTrue(bindingResult.hasErrors());
        Assertions.assertTrue(bindingResult.hasFieldErrors("registration"));
        Assertions.assertTrue(bindingResult.hasFieldErrors("vinNumber"));

    }
}
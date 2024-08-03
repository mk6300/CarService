package project.carservice.controller.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import project.carservice.repository.UserRepository;
import project.carservice.service.OrderService;
import project.carservice.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
public class AdminControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testGetUnsignedOrders() throws Exception {

        mockMvc.perform(get("/admin/unsigned-orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("unsigned-orders"))
                .andExpect(model().attribute("unsignedOrders", orderService.getUnassignedOrders()))
                .andExpect(model().attribute("mechanics", userService.AllMechanics()));
    }
 }

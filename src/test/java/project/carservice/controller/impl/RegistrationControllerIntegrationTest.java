package project.carservice.controller.impl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import project.carservice.model.entity.User;
import project.carservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {userRepository.deleteAll();
    }

    @Test
    public void testRegister() throws Exception {
        mockMvc.perform(post("/register")
                        .param("username", "Ivan9999")
                        .param("password", "12345")
                        .param("firstName", "Ivan")
                        .param("lastName", "Ivanov")
                        .param("email", "ivan@abv.bg")
                        .param("phone", "0888888888")
                        .param("confirmPassword", "12345")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        Optional<User> userOpt = userRepository.findByEmail("ivan@abv.bg");

        Assertions.assertTrue(userOpt.isPresent());

        User testUser = userOpt.get();

        Assertions.assertEquals("Ivan", testUser.getFirstName());
        Assertions.assertEquals("Ivanov", testUser.getLastName());

        Assertions.assertTrue(passwordEncoder.matches("12345", testUser.getPassword()));
    }

}

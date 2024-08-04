package project.carservice.controller.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import project.carservice.model.entity.Order;
import project.carservice.model.entity.User;
import project.carservice.model.entity.enums.OrdersStatusEnum;
import project.carservice.model.entity.enums.UserRoleEnum;
import project.carservice.repository.OrderRepository;
import project.carservice.repository.UserRepository;
import project.carservice.repository.UserRoleRepository;
import project.carservice.service.OrderService;
import project.carservice.service.UserService;


import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
public class AdminControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @BeforeEach
    public void setUp() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
        User testUser = new User();
        testUser.setUsername("testUser");
        testUser.setPassword("testPassword");
        testUser.setFirstName("Ivan");
        testUser.setLastName("Ivanov");
        testUser.setEmail("test@test.com");
        testUser.setPhone("123456789");
        testUser.getRoles().add(userRoleRepository.findByRole(UserRoleEnum.USER).orElse(null));
        userRepository.save(testUser);


    }

    @AfterEach
    public void afterAll() {
        orderRepository.deleteAll();
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

    @Test
    void testAssignOrder() throws Exception {
        User testUser = userRepository.findByUsername("testUser").orElse(null);
        Assertions.assertNotNull(testUser);
        testUser.getRoles().add(userRoleRepository.findByRole(UserRoleEnum.MECHANIC).orElse(null));

        Assertions.assertEquals(1, userRepository.findAll().size());

        Order testOrder = new Order();
        testOrder.setDescription("Test order description");
        testOrder.setDate(LocalDate.now());
        testOrder.setStatus(OrdersStatusEnum.SCHEDULED);
        orderRepository.save(testOrder);

        Assertions.assertEquals(1, orderRepository.findAll().size());

        mockMvc.perform(put("/admin/assign-mechanic")
                        .param("id", testOrder.getId().toString())
                        .param("mechanicId", testUser.getId().toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/unsigned-orders"));

        Assertions.assertEquals("testUser", orderRepository.findAll().get(0).getResponsibleMechanic().getUsername());
        Assertions.assertEquals("Ivan", orderRepository.findAll().get(0).getResponsibleMechanic().getFirstName());
    }

    @Test
    void testManageUsers() throws Exception {
        userRepository.deleteAll();
        mockMvc.perform(get("/admin/manage-users"))
                .andExpect(status().isOk())
                .andExpect(view().name("manage-users"))
                .andExpect(model().attribute("users", userService.AllUsers()))
                .andExpect(model().attribute("mechanics", userService.AllMechanics()))
                .andExpect(model().attribute("admins", userService.AllAdmins()));
    }

    @Test
    void testEditUser() throws Exception {
        User testUser = userRepository.findByUsername("testUser").orElse(null);
        Assertions.assertNotNull(testUser);

        mockMvc.perform(put("/admin/edit-user")
                        .param("id", testUser.getId().toString())
                        .param("firstName", "Ivan1")
                        .param("lastName", "Ivanov1")
                        .param("email", "test1@test.bg")
                        .param("phone", "0889999999")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/manage-users"));
        Optional<User> user = userRepository.findByUsername("testUser");
        Assertions.assertEquals("Ivan1", user.get().getFirstName());
        Assertions.assertEquals("Ivanov1", user.get().getLastName());
        Assertions.assertEquals("test1@test.bg", user.get().getEmail());
        Assertions.assertEquals("0889999999", user.get().getPhone());
    }

    @Test
    public void testManageUser() throws Exception {

        User testUser = userRepository.findByUsername("testUser").orElse(null);
        Assertions.assertNotNull(testUser);
        mockMvc.perform(post("/admin/manage")
                        .param("id", testUser.getId().toString())
                        .param("action", "makeMechanic")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/manage-users"));
        Optional<User> user = userRepository.findByUsername("testUser");
        Assertions.assertEquals(1, userRepository.findAllByRole(UserRoleEnum.MECHANIC).size());
        Assertions.assertEquals(user.get().getRoles().size(), 2);
        Assertions.assertTrue(user.get().getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.MECHANIC)));

        mockMvc.perform(post("/admin/manage")
                        .param("id", testUser.getId().toString())
                        .param("action", "makeAdmin")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/manage-users"));
        Optional<User> user2 = userRepository.findByUsername("testUser");

        Assertions.assertEquals(1, userRepository.findAllByRole(UserRoleEnum.ADMIN).size());
        Assertions.assertEquals(user2.get().getRoles().size(), 3);
        Assertions.assertTrue(user2.get().getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.ADMIN)));

        mockMvc.perform(post("/admin/manage")
                        .param("id", testUser.getId().toString())
                        .param("action", "remove")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/manage-users"));
    }

    @Test
    public void testRemoveUser() throws Exception {
        User testUser = userRepository.findByUsername("testUser").orElse(null);
        Assertions.assertEquals(1, userRepository.findAll().size());
        Assertions.assertNotNull(testUser);
        mockMvc.perform(delete("/admin/remove-user/" + testUser.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/manage-users"));
        Assertions.assertEquals(0, userRepository.findAll().size());
    }


    @Test
    public void testRemoveMechanic() throws Exception {
        User testUser = userRepository.findByUsername("testUser").orElse(null);
        Assertions.assertNotNull(testUser);
        testUser.getRoles().add(userRoleRepository.findByRole(UserRoleEnum.MECHANIC).orElse(null));
        Assertions.assertTrue(testUser.getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.MECHANIC)));

        mockMvc.perform(post("/admin/remove-mechanic")
                        .param("id", testUser.getId().toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/manage-users"));
        User testUser1 = userRepository.findByUsername("testUser").orElse(null);
        Assertions.assertNotNull(testUser1);
        Assertions.assertFalse(testUser1.getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.MECHANIC)));
    }

    @Test
    public void testRemoveAdmin() throws Exception {
        User testUser = userRepository.findByUsername("testUser").orElse(null);
        Assertions.assertNotNull(testUser);
        testUser.getRoles().add(userRoleRepository.findByRole(UserRoleEnum.ADMIN).orElse(null));
        Assertions.assertTrue(testUser.getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.ADMIN)));

        mockMvc.perform(post("/admin/remove-admin")
                        .param("id", testUser.getId().toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/manage-users"));
        User testUser1 = userRepository.findByUsername("testUser").orElse(null);
        Assertions.assertNotNull(testUser1);
        Assertions.assertFalse(testUser1.getRoles().stream().anyMatch(r -> r.getRole().equals(UserRoleEnum.ADMIN)));
    }

    @Test
    public void testRemoveOrder() throws Exception {
        Order testOrder = new Order();
        testOrder.setDescription("Test order description");
        testOrder.setDate(LocalDate.now());
        testOrder.setStatus(OrdersStatusEnum.SCHEDULED);
        orderRepository.save(testOrder);

        Assertions.assertEquals(1, orderRepository.findAll().size());

        mockMvc.perform(delete("/admin/remove-order/" + testOrder.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/unsigned-orders"));

        Assertions.assertEquals(0, orderRepository.findAll().size());
    }

}

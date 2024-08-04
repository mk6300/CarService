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
import project.carservice.model.entity.Supplier;
import project.carservice.repository.SupplierRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN", "MECHANIC"})
public class SupplierControllerImplIntegrationTest {

    private static final String SUPPLIER_CONTROLLER_PREFIX = "/supplier";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void setUp() {
        supplierRepository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        supplierRepository.deleteAll();
    }

    @Test
    public void testGetAllSuppliers() throws Exception {
        mockMvc.perform(get(SUPPLIER_CONTROLLER_PREFIX + "/suppliers"))
                .andExpect(status().isOk())
                .andExpect(view().name("suppliers"))
                .andExpect(model().attributeExists("suppliers"));
    }

    @Test
    public void testAddSupplier() throws Exception {
        mockMvc.perform(post(SUPPLIER_CONTROLLER_PREFIX + "/add-supplier")
                        .param("name", "Supplier1")
                        .param("address", "Address1")
                        .param("phoneNumber", "0888888888")
                        .param("email", "test@test.bg")
                        .param("information", "Information1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(SUPPLIER_CONTROLLER_PREFIX + "/suppliers"));
        Assertions.assertEquals(1, supplierRepository.count());
        Assertions.assertEquals("Supplier1", supplierRepository.findAll().get(0).getName());
        Assertions.assertEquals("Address1", supplierRepository.findAll().get(0).getAddress());
        Assertions.assertEquals("0888888888", supplierRepository.findAll().get(0).getPhoneNumber());
        Assertions.assertEquals("test@test.bg", supplierRepository.findAll().get(0).getEmail());
        Assertions.assertEquals("Information1", supplierRepository.findAll().get(0).getInformation());
    }

    @Test
    public void testEditSupplier() throws Exception {
        Supplier testSupplier = new Supplier();
        testSupplier.setName("Supplier");
        testSupplier.setAddress("Address");
        testSupplier.setPhoneNumber("0888888888");
        testSupplier.setEmail("test@test.bg");
        testSupplier.setInformation("Information");
        supplierRepository.save(testSupplier);
        Assertions.assertEquals(1, supplierRepository.count());

        mockMvc.perform(put(SUPPLIER_CONTROLLER_PREFIX + "/edit-supplier")
                        .param("id", testSupplier.getId().toString())
                        .param("name", "Supplier1")
                        .param("address", "Address1")
                        .param("phoneNumber", "0888999999")
                        .param("email", "test1@test1.bg")
                        .param("information", "Information1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(SUPPLIER_CONTROLLER_PREFIX + "/suppliers"));
        Assertions.assertEquals(1, supplierRepository.count());
        Assertions.assertEquals("Supplier1", supplierRepository.findAll().get(0).getName());
        Assertions.assertEquals("Address1", supplierRepository.findAll().get(0).getAddress());
        Assertions.assertEquals("0888999999", supplierRepository.findAll().get(0).getPhoneNumber());
        Assertions.assertEquals("test1@test1.bg", supplierRepository.findAll().get(0).getEmail());
        Assertions.assertEquals("Information1", supplierRepository.findAll().get(0).getInformation());
    }
}

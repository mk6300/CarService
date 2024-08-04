package project.carservice.controller.impl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import project.carservice.model.dto.addDTO.AddServiceDTO;
import project.carservice.model.dto.editDTO.EditServiceDTO;
import project.carservice.model.entity.ServiceEntity;
import project.carservice.repository.ServiceRepository;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
public class ServiceControllerImplIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ServiceRepository serviceRepository;


    @BeforeEach
    public void setUp() {
        serviceRepository.deleteAll();
    }

    @Test
    public void testAddService() throws Exception {
        AddServiceDTO addServiceDTO = new AddServiceDTO();
        addServiceDTO.setName("New Service");
        addServiceDTO.setDescription("New Service Description");
        addServiceDTO.setPrice(150.0);

        mockMvc.perform(post("/services/add-service")
                        .param("name", "New Service")
                        .param("price", "150.0")
                        .param("description", "New Service Description")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/services/manage-services"));

        var services = serviceRepository.findAll();
        Assertions.assertEquals(1, services.size());
        var savedService = services.get(0);
        Assertions.assertEquals(addServiceDTO.getName(), savedService.getName());
        Assertions.assertEquals(addServiceDTO.getDescription(), savedService.getDescription());
        Assertions.assertEquals(addServiceDTO.getPrice(), savedService.getPrice());
    }

    @Test
    public void testAddServiceWithInvalidData() throws Exception {
        BindingResult bindingResult = (BindingResult) mockMvc.perform(post("/services/add-service")
                        .param("name", "")
                        .param("price", "0")
                        .param("description", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/services/add-service"))
                .andExpect(flash().attributeExists("addServiceDTO"))
                .andExpect(flash().attributeExists("org.springframework.validation.BindingResult.addServiceDTO"))
                .andReturn().getFlashMap().get("org.springframework.validation.BindingResult.addServiceDTO");
        Assertions.assertTrue(bindingResult.hasErrors());
        Assertions.assertTrue(bindingResult.hasFieldErrors("name"));
        Assertions.assertTrue(bindingResult.hasFieldErrors("price"));
        Assertions.assertTrue(bindingResult.hasFieldErrors("description"));
        Assertions.assertEquals(0, serviceRepository.count());

    }
        @Test
    public void testEditService() throws Exception {
        var service = serviceRepository.save(new ServiceEntity("Old Service", 200.00, "Old Description"));


        EditServiceDTO editServiceDTO = new EditServiceDTO();
        editServiceDTO.setId(service.getId());
        editServiceDTO.setName("Updated Service");
        editServiceDTO.setDescription("Updated Description");
        editServiceDTO.setPrice(250.0);

        mockMvc.perform(post("/services/edit")
                        .param("id", editServiceDTO.getId().toString())
                        .param("name", editServiceDTO.getName())
                        .param("price", String.valueOf(editServiceDTO.getPrice()))
                        .param("description", editServiceDTO.getDescription())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/services/manage-services"));

        var updatedService = serviceRepository.findById(service.getId()).orElseThrow();
        Assertions.assertEquals(editServiceDTO.getName(), updatedService.getName());
        Assertions.assertEquals(editServiceDTO.getDescription(), updatedService.getDescription());
        Assertions.assertEquals(editServiceDTO.getPrice(), updatedService.getPrice());
    }

    @Test
    public void testServiceInfo() throws Exception {
        var service = serviceRepository.save(new ServiceEntity("Service", 100.00, "Description"));

        mockMvc.perform(post("/services/manage-services")
                        .param("serviceId", service.getId().toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/services/manage-services?id=" + service.getId()));
    }

    @Test
    public void testManageServices() throws Exception {
        var service = serviceRepository.save(new ServiceEntity("Service", 100.00, "Description"));

        mockMvc.perform(get("/services/manage-services")
                        .param("id", service.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("manage-services"))
                .andExpect(model().attribute("selectedServiceId", service.getId()))
                .andExpect(model().attributeExists("services"));
    }
}

package project.carservice.service;

import project.carservice.model.dto.ServiceDTO;
import project.carservice.model.dto.addDTO.AddServiceDTO;
import project.carservice.model.dto.editDTO.EditServiceDTO;
import project.carservice.model.entity.ServiceEntity;

import java.util.List;
import java.util.UUID;

public interface ServiceService {
    List <ServiceDTO> getAllServices();

    ServiceDTO getServiceDetails(UUID id);

    void addService(AddServiceDTO addServiceDTO);

    EditServiceDTO editServiceDTO(UUID id);

    void editService(EditServiceDTO editServiceDTO);

    ServiceEntity getServiceEntity(UUID id);

    ServiceDTO map(ServiceEntity service);
}

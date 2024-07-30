package project.carservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.ServiceDTO;
import project.carservice.model.dto.addDTO.AddServiceDTO;
import project.carservice.model.dto.editDTO.EditServiceDTO;
import project.carservice.model.entity.ServiceEntity;
import project.carservice.repository.ServiceRepository;
import project.carservice.service.ServiceService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    private final ModelMapper modelMapper;

    public ServiceServiceImpl(ServiceRepository serviceRepository, ModelMapper modelMapper) {
        this.serviceRepository = serviceRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ServiceDTO> getAllServices() {

        return serviceRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceDTO getServiceDetails(UUID id) {
        return serviceRepository.findById(id)
                .map(this::map)
                .orElseThrow(null);
    }

    @Override
    public void addService(AddServiceDTO addServiceDTO) {
        ServiceEntity service = new ServiceEntity();
        modelMapper.map(addServiceDTO, service);
        serviceRepository.save(service);

    }

    @Override
    public EditServiceDTO editServiceDTO(UUID id) {
        return serviceRepository.findById(id)
                .map(this::mapEditDTO)
                .orElseThrow(null);
    }

    @Override
    public void editService(EditServiceDTO editServiceDTO) {
        ServiceEntity service = serviceRepository.findById(editServiceDTO.getId())
                .orElseThrow(null);
        modelMapper.map(editServiceDTO, service);
        serviceRepository.save(service);
    }

    @Override
    public ServiceEntity getServiceEntity(UUID id) {
        return serviceRepository.findById(id).orElseThrow();
    }

    @Override
    public ServiceDTO map(ServiceEntity service) {
        return modelMapper.map(service, ServiceDTO.class);
    }

    private EditServiceDTO mapEditDTO(ServiceEntity service) {
        return modelMapper.map(service, EditServiceDTO.class);
    }
}

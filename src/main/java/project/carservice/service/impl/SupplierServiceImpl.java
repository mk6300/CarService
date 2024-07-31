package project.carservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.addDTO.AddSupplierDTO;
import project.carservice.model.dto.SupplierDTO;
import project.carservice.model.entity.Supplier;
import project.carservice.repository.SupplierRepository;
import project.carservice.service.PartService;
import project.carservice.service.SupplierService;
import project.carservice.service.exceptions.SupplierNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    private final PartService partService;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, PartService partService, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.partService = partService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addSupplier(AddSupplierDTO addSupplierDTO) {
        if (!supplierRepository.existsByName(addSupplierDTO.getName())) {
            supplierRepository.save(modelMapper.map(addSupplierDTO, Supplier.class));
        }
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
        return supplierRepository.findAll().stream()
                .map(supplier -> modelMapper.map(supplier, SupplierDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SupplierDTO getSupplierById(UUID id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new SupplierNotFoundException("supplier not found"));
        return modelMapper.map(supplier, SupplierDTO.class);
    }

    @Override
    public void editSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.findById(supplierDTO.getId()).orElseThrow(() -> new SupplierNotFoundException("supplier not found"));
        modelMapper.map(supplierDTO, supplier);
        supplierRepository.save(supplier);
    }

    @Override
    public void removeSupplier(UUID supplierId) {
        partService.deleteAllPartsFromSupplier(supplierId);
        supplierRepository.deleteById(supplierId);
    }
}

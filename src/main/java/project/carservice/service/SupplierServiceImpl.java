package project.carservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.AddSupplierDTO;
import project.carservice.model.dto.SupplierDTO;
import project.carservice.model.entity.Supplier;
import project.carservice.repository.SupplierRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
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
}

package project.carservice.service;

import project.carservice.model.dto.AddDTOs.AddSupplierDTO;
import project.carservice.model.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    void addSupplier(AddSupplierDTO addSupplierDTO);

    List<SupplierDTO> getAllSuppliers();
}

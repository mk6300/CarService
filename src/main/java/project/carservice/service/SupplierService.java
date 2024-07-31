package project.carservice.service;

import project.carservice.model.dto.addDTO.AddSupplierDTO;
import project.carservice.model.dto.SupplierDTO;
import project.carservice.model.entity.Supplier;

import java.util.List;
import java.util.UUID;

public interface SupplierService {
    void addSupplier(AddSupplierDTO addSupplierDTO);

    List<SupplierDTO> getAllSuppliers();

    SupplierDTO getSupplierById(UUID id);

    void editSupplier(SupplierDTO supplierDTO);

    void removeSupplier(UUID supplierId);
}

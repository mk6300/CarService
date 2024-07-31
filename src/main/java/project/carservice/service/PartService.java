package project.carservice.service;

import project.carservice.model.dto.addDTO.AddPartDTO;
import project.carservice.model.dto.PartDTO;
import project.carservice.model.dto.editDTO.EditPartDTO;

import java.util.List;
import java.util.UUID;

public interface PartService {
    void addPart (AddPartDTO addPartDTO);


    PartDTO getPartDetails(Long id);

    List<PartDTO> getAllParts();

    void deletePart(Long id);

    void deleteAllPartsFromSupplier(UUID id);

    void editPart(Long id, PartDTO PartDTO);

}

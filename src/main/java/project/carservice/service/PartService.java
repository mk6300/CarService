package project.carservice.service;

import project.carservice.model.dto.addDTO.AddPartDTO;
import project.carservice.model.dto.PartDTO;

import java.util.List;

public interface PartService {
    void addPart (AddPartDTO addPartDTO);


    PartDTO getPartDetails(Long id);

    List<PartDTO> getAllParts();

    void deletePart(Long id);
}

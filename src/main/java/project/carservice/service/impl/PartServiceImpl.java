package project.carservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import project.carservice.model.dto.addDTO.AddPartDTO;
import project.carservice.model.dto.PartDTO;
import project.carservice.service.PartService;

import java.util.List;
import java.util.UUID;

@Service
public class PartServiceImpl implements PartService {

    private final Logger LOGGER = LoggerFactory.getLogger(PartServiceImpl.class);
    private final RestClient restClient;

    public PartServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }


    @Override
    public void addPart(AddPartDTO addPartDTO) {
        LOGGER.info("Adding new part...");

        restClient
                .post()
                .uri("http://localhost:8081/parts/add-part")
                .contentType(MediaType.APPLICATION_JSON)
                .body(addPartDTO)
                .retrieve();
    }

    @Override
    public PartDTO getPartDetails(Long id) {

        return restClient
                .get()
                .uri("http://localhost:8081/parts/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(PartDTO.class);
    }

    @Override
    public List<PartDTO> getAllParts() {
        LOGGER.info("Get all parts...");

        return restClient
                .get()
                .uri("http://localhost:8081/parts")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public void deletePart(Long id) {
        LOGGER.info("Deleting part...");

        restClient
                .delete()
                .uri("http://localhost:8081/parts/{id}", id)
                .retrieve();
    }

    @Override
    public void deleteAllPartsFromSupplier(UUID id) {
        LOGGER.info("Deleting a;; parts form Suppler...");

        restClient
                .delete()
                .uri("http://localhost:8081/parts/remove-all/{id}", id)
                .retrieve();
    }

    @Override
    public void editPart(Long id, PartDTO partDTO) {
        LOGGER.info("Editing part...");

        restClient
                .put()
                .uri("http://localhost:8081/parts/edit-part", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(partDTO)
                .retrieve();
    }

}


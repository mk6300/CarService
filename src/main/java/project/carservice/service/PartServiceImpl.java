package project.carservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import project.carservice.model.dto.AddPartDTO;
import project.carservice.model.dto.PartDTO;

import java.util.List;

@Service
public class PartServiceImpl implements PartService {

    private Logger LOGGER = LoggerFactory.getLogger(PartServiceImpl.class);
    private final RestClient restClient;

    public PartServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }


    @Override
    public void addPart(AddPartDTO addPartDTO) {
        LOGGER.info("Adding new part...");

        restClient
                .post()
                .uri("http://localhost:8081/parts")
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
}

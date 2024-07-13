package project.carservice.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import project.carservice.model.dto.CarQueryMakeDTO;
import project.carservice.model.dto.MakesResponse;
import project.carservice.model.entity.CarMake;
import project.carservice.repository.CarMakeRepository;

import java.util.List;
import java.util.Map;

public class CarQueryServiceImpl implements CarQueryService {
    private static final String BASE_URL = "https://www.carqueryapi.com/api/0.3/?callback=?&cmd=";
    private final RestClient restClient;
    private final CarMakeRepository carMakeRepository;


    public CarQueryServiceImpl(RestClient restClient, CarMakeRepository carMakeRepository) {
        this.restClient = restClient;
        this.carMakeRepository = carMakeRepository;
    }

    @Override
    public boolean hasInitializedMakes() {
        return carMakeRepository.count() > 0;
    }
    public void initializeCarMakes() {
        List<CarQueryMakeDTO> carMakes = fetchCarMakesFromApi();

        for (CarQueryMakeDTO carMakeData : carMakes) {
            String name = carMakeData.getMake_display();
            String country = carMakeData.getMake_country();

            carMakeRepository.findByName(name).orElseGet(() -> {
                CarMake carMake = new CarMake();
                carMake.setName(name);
                carMake.setCountry(country);
                return carMakeRepository.save(carMake);
            });
        }
    }

    private List<CarQueryMakeDTO> fetchCarMakesFromApi() {
        String url = BASE_URL+"getMakes";

        try {
            ResponseEntity<MakesResponse> response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .toEntity(MakesResponse.class);

            return response.getBody().getMakes();
        } catch (RestClientException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}





package project.carservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean
    public RestClient partsRestClient(PartsApiConfig partsApiConfig){
        return RestClient
                .builder()
                .baseUrl(partsApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON.getType())
                .build();
    }
}

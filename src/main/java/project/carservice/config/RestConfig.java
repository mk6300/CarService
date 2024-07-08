package project.carservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Bean
    public RestClient ordersRestClient(OrderApiConfig orderApiConfig){
        return RestClient
                .builder()
                .baseUrl(orderApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON.getType())
                .build();
    }
}

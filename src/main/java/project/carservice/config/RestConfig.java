package project.carservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.client.RestClient;
import project.carservice.service.JwtService;
import project.carservice.service.UserService;

import java.util.Map;

@Configuration
public class RestConfig {

    @Bean
    public RestClient partsRestClient(PartsApiConfig partsApiConfig,
                                      ClientHttpRequestInterceptor requestInterceptor){
        return RestClient
                .builder()
                .baseUrl(partsApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .requestInterceptor(requestInterceptor)
                .build();
    }

    @Bean
    public ClientHttpRequestInterceptor requestInterceptor(UserService userService, JwtService jwtService){
        return (request, body, execution) -> {
            userService.getCurrentUserDetails()
                    .ifPresent(user -> {
                String bearerToken = jwtService.generateToken(user.getId().toString(),
                        Map.of(
                                "roles",
                                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                        )
                );
                System.out.println("BEARER TOKEN: " + bearerToken);
                request.getHeaders().setBearerAuth(bearerToken);
            });
           return execution.execute(request, body);
        };
    }
}

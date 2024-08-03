package project.carservice.service;

import java.util.Map;
import java.util.UUID;

public interface JwtService {
    String generateToken(String userId, Map<String, Object> claims);
}

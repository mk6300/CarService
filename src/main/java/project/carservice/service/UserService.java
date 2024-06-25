package project.carservice.service;

import org.springframework.stereotype.Service;
import project.carservice.model.dto.UserDTO;


public interface UserService {
    UserDTO findUserByUsername(String username);

    UserDTO findUserByEmail(String email) ;
}

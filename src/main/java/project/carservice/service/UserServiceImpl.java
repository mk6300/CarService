package project.carservice.service;

import org.springframework.stereotype.Service;
import project.carservice.model.dto.UserDTO;
import project.carservice.model.entity.User;
import project.carservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }

    private UserDTO mapUserDTO(User user) {

            return new UserDTO();
    }


    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }
}

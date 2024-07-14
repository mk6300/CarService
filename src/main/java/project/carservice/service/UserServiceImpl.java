package project.carservice.service;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.RegisterUserDTO;
import project.carservice.model.dto.UserDTO;
import project.carservice.model.entity.User;
import project.carservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final HttpSession session;


    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder encoder, HttpSession session) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.session = session;
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            return null;
        }

        return this.mapUserDTO(user);
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        User user = this.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        return encoder.matches(password, user.getPassword());
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void register(RegisterUserDTO registerUserDTO) {
        this.userRepository.save(this.mapUser(registerUserDTO));

    }

    private User mapUser(RegisterUserDTO registerUserDTO) {
        User user = this.modelMapper.map(registerUserDTO, User.class);
        user.setPassword(encoder.encode(registerUserDTO.getPassword()));
        return user;
    }

    private UserDTO mapUserDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }


}

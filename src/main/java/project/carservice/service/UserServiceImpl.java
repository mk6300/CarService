package project.carservice.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.carservice.model.dto.RegisterUserDTO;
import project.carservice.model.dto.UserDTO;
import project.carservice.model.entity.User;
import project.carservice.repository.UserRepository;
import project.carservice.util.LoggedUser;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final PasswordEncoder encoder;
    private final HttpSession session;


    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, PasswordEncoder encoder, HttpSession session) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
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
    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    @Override
    public void register(RegisterUserDTO registerDTO) {
        this.userRepository.save(this.mapUser(registerDTO));
        this.login(registerDTO.getUsername());
    }

    @Override
    public void logout() {
        this.session.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }

    private User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    private User mapUser(RegisterUserDTO registerDTO) {
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encoder.encode(registerDTO.getPassword()));
        return user;
    }

    private UserDTO mapUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setCars(user.getCars());
        userDTO.setRoles(user.getRoles());
        userDTO.setOrders(user.getOrders());
        userDTO.setOrdersInProgress(user.getOrdersInProgress());

        return userDTO;
    }
}

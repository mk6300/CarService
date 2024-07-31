package project.carservice.service.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.carservice.model.dto.RegisterUserDTO;
import project.carservice.model.dto.UserDTO;
import project.carservice.model.dto.editDTO.EditUserDTO;
import project.carservice.model.entity.User;
import project.carservice.model.entity.UserRole;
import project.carservice.model.entity.enums.UserRoleEnum;
import project.carservice.repository.UserRepository;
import project.carservice.repository.UserRoleRepository;
import project.carservice.service.UserService;
import project.carservice.service.exceptions.RoleNotFoundException;
import project.carservice.service.exceptions.UserNotFoundException;
import project.carservice.service.session.AppUserDetailsService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AppUserDetailsService appUserDetailsService;
    private final UserRoleRepository userRoleRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder encoder, AppUserDetailsService appUserDetailsService, UserRoleRepository userRoleRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.appUserDetailsService = appUserDetailsService;
        this.userRoleRepository = userRoleRepository;
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
    public UserDTO getUserById(UUID id) {
        return this.mapUserDTO(this.userRepository.findById(id).orElse(null));
    }

    @Override
    public EditUserDTO getUserEditById(UUID id) {
        return this.modelMapper.map(this.userRepository.findById(id).orElse(null), EditUserDTO.class);
    }

    @Override
    public void register(RegisterUserDTO registerUserDTO) {
        User user = this.mapUser(registerUserDTO);
        user.setPassword(encoder.encode(registerUserDTO.getPassword()));
        user.getRoles().add(userRoleRepository.findByRole(UserRoleEnum.USER).orElse(null));
        this.userRepository.save(user);

    }

    private User mapUser(RegisterUserDTO registerUserDTO) {
        return this.modelMapper.map(registerUserDTO, User.class);
    }

    private UserDTO mapUserDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @Override
    public UserDetails getCurrentUserDetails() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return this.appUserDetailsService.loadUserByUsername(principal.getName());
    }

    @Override
    public User getCurrentUser() {
        return this.getUserByUsername(this.getCurrentUserDetails().getUsername());
    }

    @Override
    public List<UserDTO> AllMechanics() {
        return this.userRepository.findAllByRole(UserRoleEnum.MECHANIC).stream()
                .map(this::mapUserDTO)
                .toList();

    }

    @Override
    public List<UserDTO> AllUsers() {
        return this.userRepository.findAllByRole(UserRoleEnum.USER).stream()
                .map(this::mapUserDTO)
                .toList();

    }

    @Override
    public void editUser(EditUserDTO editUserDTO) {
        userRepository.findById(editUserDTO.getId()).ifPresent(user -> {
            user.setFirstName(editUserDTO.getFirstName());
            user.setLastName(editUserDTO.getLastName());
            user.setEmail(editUserDTO.getEmail());
            user.setPhone(editUserDTO.getPhone());
            userRepository.save(user);
        });
    }
    @Override
    @Secured("ROLE_ADMIN")
    public void makeMechanic(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        UserRole mechanicRole = userRoleRepository.findByRole(UserRoleEnum.MECHANIC)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
        user.getRoles().add(mechanicRole);
        userRepository.save(user);
    }
    @Override
    @Secured("ROLE_ADMIN")
    public void removeUser(UUID userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Secured("ROLE_ADMIN")
    @Transactional
    public void removeMechanic(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        LOGGER.debug("User found: {}", user);
        UserRole mechanicRole = userRoleRepository.findByRole(UserRoleEnum.MECHANIC)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
        LOGGER.debug("Mechanic role found: {}", mechanicRole);
        user.getRoles().remove(mechanicRole);
            userRepository.save(user);
    }

    @Override
    public List<UserDTO> AllAdmins() {
        return this.userRepository.findAllByRole(UserRoleEnum.ADMIN).stream()
                .map(this::mapUserDTO)
                .toList();
    }

    @Override
    @Secured("ROLE_ADMIN")
    @Transactional
    public void removeAdmin(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        UserRole adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
        user.getRoles().remove(adminRole);
        userRepository.save(user);

    }

    @Override
    @Secured("ROLE_ADMIN")
    public void makeAdmin(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        UserRole adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
        user.getRoles().add(adminRole);
        userRepository.save(user);

    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
}

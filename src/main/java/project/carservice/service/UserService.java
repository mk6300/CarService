package project.carservice.service;

import project.carservice.model.dto.addDTO.RegisterUserDTO;
import project.carservice.model.dto.UserDTO;
import project.carservice.model.dto.editDTO.EditUserDTO;
import project.carservice.model.entity.User;
import project.carservice.model.user.AppUserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface UserService {
    UserDTO findUserByUsername(String username);

    UserDTO findUserByEmail(String email) ;

    User getUserByUsername(String username);

    UserDTO getUserById(UUID id);

    EditUserDTO getUserEditById(UUID id);

    void register(RegisterUserDTO registerDTO);


    Optional<AppUserDetails> getCurrentUserDetails();

    User getCurrentUser();

    List<UserDTO> AllMechanics();

    List<UserDTO> AllUsers();

    void editUser(EditUserDTO editUserDTO);

    void makeMechanic(UUID id);

    void removeUser(UUID id);

    void removeMechanic(UUID id);

    List<UserDTO> AllAdmins();

    void removeAdmin(UUID id);

    void makeAdmin(UUID id);

    Optional<User> findById(UUID id);

    int AllMechanicsCount();

    int AllUsersCount();
}

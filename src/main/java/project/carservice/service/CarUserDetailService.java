package project.carservice.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.carservice.model.entity.User;
import project.carservice.model.entity.UserRole;
import project.carservice.model.entity.enums.UserRoleEnum;
import project.carservice.model.user.CarUserDetails;
import project.carservice.repository.UserRepository;

@Service
public class CarUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CarUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(CarUserDetailService::map)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with username " + username + " not found!"));
    }

    private static UserDetails map(User user) {

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream().map(UserRole::getRole).map(CarUserDetailService::map).toList())
                .build();

    }

    private static GrantedAuthority map(UserRoleEnum role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role
        );
    }
}


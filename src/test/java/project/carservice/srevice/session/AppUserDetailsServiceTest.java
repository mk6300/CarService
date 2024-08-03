package project.carservice.srevice.session;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import project.carservice.model.entity.User;
import project.carservice.model.entity.UserRole;
import project.carservice.model.entity.enums.UserRoleEnum;
import project.carservice.repository.UserRepository;
import project.carservice.service.session.AppUserDetailsService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppUserDetailsServiceTest {

    private AppUserDetailsService appUserDetailsService;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        appUserDetailsService = new AppUserDetailsService(mockUserRepository);
    }

    @Test
    public void testLoadUserByUsername() {
        User testUser = new User();
        testUser.setUsername("Ivan9");
        testUser.setEmail("email@abvb.bg");
        testUser.setPassword("12345");
        testUser.setFirstName("Ivan");
        testUser.setLastName("Ivanov");
        testUser.setPhone("0888888888");
        UserRole userRole = new UserRole().setRole(UserRoleEnum.USER);
        UserRole adminRole = new UserRole().setRole(UserRoleEnum.ADMIN);
        testUser.setRoles(List.of(userRole, adminRole));


        when(mockUserRepository.findByUsername("Ivan9")).thenReturn(java.util.Optional.of(testUser));

        UserDetails userDetails = appUserDetailsService.loadUserByUsername("Ivan9");

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUser.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(2, userDetails.getAuthorities().size());
    }

    @Test
    void testLoadUserByUsernameWithNullUser() {
assertThrows(UsernameNotFoundException.class, () ->
            appUserDetailsService.loadUserByUsername("Ivan99"));
    }
}

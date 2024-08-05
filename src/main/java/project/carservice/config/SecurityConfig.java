package project.carservice.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        // Setup which URL-s are available to who
                        authorizeRequests -> authorizeRequests
                                // all static resources to "common locations" (css, images, js) are available to anyone
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                // some more resources for all users
                                .requestMatchers("/","/login","/login-error", "/register", "/service", "/about", "/contact", "/subscribe", "/unsubscribe").permitAll()

                                .requestMatchers("/home", "/users/**","/cars/**").authenticated()
                                .requestMatchers("/orders/my-orders").authenticated()
                                .requestMatchers("/orders/add-order").authenticated()
                                .requestMatchers("/orders/history").authenticated()
                                .requestMatchers("/parts/**","/mechanic/**","/orders/**","/suppliers/**").hasAnyRole("ADMIN", "MECHANIC")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/services/**").hasRole("ADMIN")



                                // all other URL-s should be authenticated.
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(formLogin -> formLogin
                        // Where is our custom login form?
                        .loginPage("/login")
                        // what is the name of the username parameter in the Login POST request?
                        .usernameParameter("username")
                        // what is the name of the password parameter in the Login POST request?
                        .passwordParameter("password")
                        // What will happen if the login is successful
                        .defaultSuccessUrl("/home", true)
                        // What will happen if the login fails
                        .failureUrl("/login-error")
                )
                .logout(
                        logout -> logout
                                // what is the logout URL?
                                .logoutUrl("/logout")
                                // Where to go after successful logout?
                                .logoutSuccessUrl("/").permitAll()
                                // invalidate the session after logout.
                                .invalidateHttpSession(true)
                )

                .csrf((csrf) -> csrf .ignoringRequestMatchers("/subscribe", "/unsubscribe"))


                .build();
    }

//    @Bean
//    public CarUserDetailService userDetailsService(UserRepository userRepository) {
//        return new CarUserDetailService(userRepository);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder
                .defaultsForSpringSecurity_v5_8();
    }
}

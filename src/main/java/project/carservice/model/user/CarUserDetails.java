package project.carservice.model.user;

import org.springframework.security.core.GrantedAuthority;
import project.carservice.model.entity.User;

import java.util.Collection;

public class CarUserDetails extends User {
    private final String firstName;
    private final String lastName;

    public CarUserDetails(String username,
                          String password,
                          Collection<? extends GrantedAuthority> authorities,
                          String firstName,
                          String lastName
    ) {
        super(username, password, authorities);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        if (firstName != null) {
            fullName.append(firstName);
        }
        if (lastName != null) {
            if (!fullName.isEmpty()) {
                fullName.append(" ");
            }
            fullName.append(lastName);
        }

        return fullName.toString();
    }
}

package project.carservice.model.dto;

import jakarta.persistence.*;
import project.carservice.model.entity.Car;
import project.carservice.model.entity.Order;
import project.carservice.model.entity.enums.UserRoleEnum;

import java.util.Set;

public class UserDTO {
    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    private String phone;

    private UserRoleEnum role;

    private Set<Car> cars;

    private Set<Order> orders;

   private Set<Order> ordersInProgress;

}

package project.carservice.model.dto;

import jakarta.persistence.*;
import project.carservice.model.entity.Car;
import project.carservice.model.entity.Order;
import project.carservice.model.entity.UserRole;
import project.carservice.model.entity.enums.UserRoleEnum;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class UserDTO {

    private UUID id;
    private String firstName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    private String lastName;

    private String username;

    private String password;

    private String email;

    private String phone;

    private List<UserRole> roles;

    private Set<Car> cars;

    private Set<Order> orders;

    private Set<Order> ordersInProgress;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<UserRole> getRoles() {
        return roles;
    }
    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Order> getOrdersInProgress() {
        return ordersInProgress;
    }

    public void setOrdersInProgress(Set<Order> ordersInProgress) {
        this.ordersInProgress = ordersInProgress;
    }
}

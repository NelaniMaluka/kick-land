package com.examplekicklaandwebsite.KickLaand.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String firstname;

    public String lastname;

    @Valid
    @Column(unique = true)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Please provide a valid email address")
    public String email;

    @Valid
    public String password;

    @OneToMany(mappedBy = "userId")
    public List<UserCarts> userCart;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    public List<UserOrders> orders;

    @Pattern(regexp = "^\\+?[0-9]{1,15}$", message = "Please provide a valid phone number")
    public String phonenumber;

    public String address;

    // Default constructor
    public UserAccount() {}

    // Constructor for email and password
    public UserAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Constructor for full name, email, and password
    public UserAccount(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserCarts> getUserCart() {
        return userCart;
    }

    public void setUserCart(List<UserCarts> userCart) {
        this.userCart = userCart;
    }

    public List<UserOrders> getOrders() {
        return orders;
    }

    public void setOrders(List<UserOrders> orders) {
        this.orders = orders;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

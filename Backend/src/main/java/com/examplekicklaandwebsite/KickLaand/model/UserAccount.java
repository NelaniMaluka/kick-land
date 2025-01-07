package com.examplekicklaandwebsite.KickLaand.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String firstname;

    public String lastname;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Please provide a valid email address")
    public String email;

    @Column(nullable = false)
    @NotNull(message = "Password cannot be null")
    public String password;

    private USER_ROLE role;

    @OneToMany(mappedBy = "userId")
    public List<UserCarts> userCart;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    public List<UserOrders> orders;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    public List<CompletedOrders> completedOrders;
    
    @OneToOne(mappedBy= "user")
    private ForgotPassword forgotPassword;

    @Pattern(regexp = "^\\+?[0-9]{1,15}$", message = "Please provide a valid phone number")
    public String phonenumber;

}

package com.examplekicklaandwebsite.KickLaand.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserOrders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UserAccount userId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	List<UserCarts> products;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "orderId")
	List<CompletedOrders> completedOrders;

	@Column(nullable = false)
	@Valid
	@Email(message = "Invalid email format")
	private String email;

	@Column(nullable = false)
	@NotNull(message = "Address cannot be null")
	private String address;

	@Column(nullable = false)
	@NotNull(message = "Province cannot be null")
	private String province;

	@Column(nullable = false)
	@NotNull(message = "Firstname cannot be null")
	private String firstname;

	@Column(nullable = false)
	@NotNull(message = "Lastname cannot be null")
	private String lastname;

	@Column(nullable = false)
	@NotNull(message = "ZIP Code cannot be null")
	private String ZIPCode;

	@Column(nullable = false)
	@NotNull(message = "Order Date cannot be null")
	private LocalDateTime orderDate;

	@Column(nullable = false)
	@NotNull(message = "Delivery Date cannot be null")
	private LocalDateTime deliveryDate;

	@Column(nullable = false)
	@Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number")
	private String phoneNumber;

}

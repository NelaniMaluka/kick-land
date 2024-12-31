package com.examplekicklaandwebsite.KickLaand.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

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

	@Valid
	@Email(message = "Invalid email format")
	private String email;

	@NotNull
	private String address;

	@NotNull
	private String province;

	@NotNull
	private String firstname;

	@NotNull
	private String lastname;

	@NotNull
	private String ZIPCode;

	@NotNull
	private LocalDateTime orderDate;

	@NotNull
	private LocalDateTime deliveryDate;

	@Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number")
	private String phoneNumber;

}

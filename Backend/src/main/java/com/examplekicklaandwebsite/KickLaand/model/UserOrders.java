package com.examplekicklaandwebsite.KickLaand.model;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserOrders {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer orderId;

		@ManyToOne
		@JoinColumn(name = "userId")
		private UserAccount userId;

		@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
		@JoinColumn(name= "orderId")
		List <UserCarts> products;
		
		@Valid
		@Pattern(regexp = "^\\+?[0-9\\-\\s]*$", message = "Please provide a valid phone number")
		private String userEmail;
		
		@NotNull
		private String userAddress;
		
		@Pattern(
		        regexp = "^\\+?[0-9\\-\\s]*$", 
		        message = "Please provide a valid phone number"
		    )
		private String userPhoneNumber;

}

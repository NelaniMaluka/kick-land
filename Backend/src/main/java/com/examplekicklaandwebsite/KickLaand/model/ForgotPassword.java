package com.examplekicklaandwebsite.KickLaand.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ForgotPassword {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fpid;
	
	@Column(nullable = false)
	@NotNull(message = "OTP cannot be null")
	private Integer otp;
	
	@Column(nullable = false)
	@NotNull(message = "Expiration Date cannot be null")
	private Date expirationTime;

	@Column(nullable = false)
	@OneToOne
	@NotNull(message = "User cannot be null")
	private UserAccount user;
	
}

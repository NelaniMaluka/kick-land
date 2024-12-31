package com.examplekicklaandwebsite.KickLaand.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
	private Integer otp;
	
	@Column(nullable = false)
	private Date expirationTime;
	
	@OneToOne
	private UserAccount user;
	
}

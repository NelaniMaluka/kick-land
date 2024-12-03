package com.examplekicklaandwebsite.KickLaand.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplekicklaandwebsite.KickLaand.model.Newsletter;

public interface NewsletterRepository extends JpaRepository <Newsletter, String>{
	
	public Newsletter findByEmail(String email);

}
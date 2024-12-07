package com.examplekicklaandwebsite.KickLaand.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer>{
	
	UserAccount findByEmail(String email);

}

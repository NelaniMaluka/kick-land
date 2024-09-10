package com.examplekicklaandwebsite.KickLaand.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer>{
	
	UserAccount findByEmailAndPassword(String email, String password);
	
	UserAccount findByEmail(String email);
	

}

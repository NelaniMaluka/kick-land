package com.examplekicklaandwebsite.KickLaand.Login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Long>{
	
	public UserAccount findByUsernameAndEmail(String username, String email);

}

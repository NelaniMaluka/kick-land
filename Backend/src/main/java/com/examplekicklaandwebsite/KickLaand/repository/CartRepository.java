package com.examplekicklaandwebsite.KickLaand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.examplekicklaandwebsite.KickLaand.model.UserAccount;
import com.examplekicklaandwebsite.KickLaand.model.UserCarts;

public interface CartRepository extends JpaRepository<UserCarts, Integer> {
	
	@Transactional
	void deleteByProductIdAndUserId(Integer productId, UserAccount user);

}


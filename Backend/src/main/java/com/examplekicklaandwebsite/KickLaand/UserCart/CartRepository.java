package com.examplekicklaandwebsite.KickLaand.UserCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends JpaRepository<UserCarts, Integer> {
	@Transactional
	void deleteByProductIdAndUserId(Integer productId, Integer userId);
}


package com.examplekicklaandwebsite.KickLaand.UserCart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Transactional
    void deleteByProductIdAndUser_Id(Integer productId, Integer userId);
}


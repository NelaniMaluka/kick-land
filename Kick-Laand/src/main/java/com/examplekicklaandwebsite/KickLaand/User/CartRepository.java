package com.examplekicklaandwebsite.KickLaand.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByUser(UserAccount user);
    
    //Cart findByProductidandUser(Long id);

}

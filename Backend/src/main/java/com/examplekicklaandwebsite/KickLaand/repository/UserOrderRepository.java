package com.examplekicklaandwebsite.KickLaand.repository;

import com.examplekicklaandwebsite.KickLaand.model.UserOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrders,Integer>{

}

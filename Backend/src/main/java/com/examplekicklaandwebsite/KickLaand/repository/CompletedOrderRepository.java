package com.examplekicklaandwebsite.KickLaand.repository;

import com.examplekicklaandwebsite.KickLaand.model.CompletedOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedOrderRepository extends JpaRepository<CompletedOrders, Integer> {
}

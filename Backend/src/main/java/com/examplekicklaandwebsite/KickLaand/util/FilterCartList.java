package com.examplekicklaandwebsite.KickLaand.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.examplekicklaandwebsite.KickLaand.model.UserCarts;

public class FilterCartList {
	
	private FilterCartList () {}
	
    public static List<Map<String, Object>> getFilteredCartList(List<UserCarts> userCart) {
        return userCart.stream()
                .map(cart -> {
                    Map<String, Object> filteredCartItem = new HashMap<>();
                    filteredCartItem.put("id", cart.getUserCartId());
                    filteredCartItem.put("productId", cart.getProductId());
                    filteredCartItem.put("quantity", cart.getQuantity());
                    filteredCartItem.put("size", cart.getProductSize());
                    filteredCartItem.put("price", cart.getPrice());
                    return filteredCartItem;
                })
                .collect(Collectors.toList());
    }

}

package com.examplekicklaandwebsite.KickLaand.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.examplekicklaandwebsite.KickLaand.model.CompletedOrders;
import com.examplekicklaandwebsite.KickLaand.model.UserCarts;

public class FilterLists {

    private FilterLists() {
    }

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

    public static List<Map<String, Object>> getFilteredOrderList(List<CompletedOrders> userOrder) {
        return userOrder.stream()
                .map(cart -> {
                    Map<String, Object> filteredOrderItem = new HashMap<>();
                    filteredOrderItem.put("completedOrderId", cart.getComplatedOrderId());
                    filteredOrderItem.put("productId", cart.getProductId());
                    filteredOrderItem.put("quantity", cart.getQuantity());
                    filteredOrderItem.put("size", cart.getProductSize());
                    filteredOrderItem.put("price", cart.getPrice());
                    return filteredOrderItem;
                })
                .collect(Collectors.toList());
    }

}

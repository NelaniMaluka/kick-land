package com.examplekicklaandwebsite.KickLaand.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.examplekicklaandwebsite.KickLaand.model.CompletedOrders;
import com.examplekicklaandwebsite.KickLaand.model.UserCarts;
import com.examplekicklaandwebsite.KickLaand.model.UserOrders;

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

    public static List<Map<String, Object>> getFilteredOrderList(List<UserOrders> userOrder) {
        return userOrder.stream()
                .map(order -> {
                    Map<String, Object> filteredOrderItem = new HashMap<>();
                    filteredOrderItem.put("orderId", order.getOrderId());
                    filteredOrderItem.put("email", order.getEmail());
                    filteredOrderItem.put("address", order.getAddress());
                    filteredOrderItem.put("province", order.getProvince());
                    filteredOrderItem.put("firstname", order.getFirstname());
                    filteredOrderItem.put("lastname", order.getLastname());
                    filteredOrderItem.put("ZIPCode", order.getZIPCode());
                    filteredOrderItem.put("phoneNumber", order.getPhoneNumber());
                    filteredOrderItem.put("orders", getFilteredCompletedOrderList(order.getCompletedOrders()));
                    filteredOrderItem.put("orderDate", order.getOrderDate());
                    filteredOrderItem.put("deliveryDate", order.getDeliveryDate());
                    return filteredOrderItem;
                })
                .collect(Collectors.toList());
    }

    public static List<Map<String, Object>> getFilteredCompletedOrderList(List<CompletedOrders> userOrder) {
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

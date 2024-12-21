package com.examplekicklaandwebsite.KickLaand.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompletedOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer complatedOrderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserAccount userId;

    @NotNull(message = "Product ID cannot be null")
    private Integer productId;

    @NotNull(message = "Product size cannot be null")
    private String productSize;

    @NotNull(message = "Product quantity cannot be null")
    private Integer quantity;

    @NotNull
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private UserOrders order;
}

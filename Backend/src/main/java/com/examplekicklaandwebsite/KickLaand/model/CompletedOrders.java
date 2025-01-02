package com.examplekicklaandwebsite.KickLaand.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompletedOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer complatedOrderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserAccount userId;

    @Column(nullable = false)
    @NotNull(message = "Product ID cannot be null")
    private Integer productId;

    @Column(nullable = false)
    @NotNull(message = "Product size cannot be null")
    private String productSize;

    @Column(nullable = false)
    @NotNull(message = "Product quantity cannot be null")
    private Integer quantity;

    @Column(nullable = false)
    @NotNull(message = "Product price cannot be null")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private UserOrders order;
}

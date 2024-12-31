package com.examplekicklaandwebsite.KickLaand.request;

import jakarta.validation.constraints.NotNull;

public record CartRequest (
    @NotNull(message = "Product Id cannot be null")
    Integer productId,

    Integer quantity
){

};


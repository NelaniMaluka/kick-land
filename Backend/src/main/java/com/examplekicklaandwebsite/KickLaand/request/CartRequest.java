package com.examplekicklaandwebsite.KickLaand.request;

import jakarta.validation.constraints.NotNull;

public record CartRequest (
    @NotNull
    Integer productId,

    Integer quantity
){

};


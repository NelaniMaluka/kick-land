package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.model.UserCarts;
import com.examplekicklaandwebsite.KickLaand.response.PaymentResponse;
import com.stripe.exception.StripeException;

import java.util.List;

public interface PaymentService {

    PaymentResponse createPaymentLink(List <UserCarts> userCarts) throws StripeException;
}

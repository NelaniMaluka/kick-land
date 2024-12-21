package com.examplekicklaandwebsite.KickLaand.service.impl;

import com.examplekicklaandwebsite.KickLaand.model.UserCarts;
import com.examplekicklaandwebsite.KickLaand.response.PaymentResponse;
import com.examplekicklaandwebsite.KickLaand.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Value("${payment.success.url}")
    private String successUrl;

    @Value("${payment.cancel.url}")
    private String cancelUrl;

    @Override
    public PaymentResponse createPaymentLink(List<UserCarts> userCarts) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        try {
            // Build line items for the session
            List<SessionCreateParams.LineItem> lineItems = userCarts.stream()
                    .map(cart -> SessionCreateParams.LineItem.builder()
                            .setQuantity(1L)  // Assuming each cart item has a quantity of 1
                            .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                    .setCurrency("ZAR")  // South African Rand
                                    .setUnitAmount(cart.getPrice() * 100L)  // Convert to cents
                                    .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                            .setName("name")  // Set actual product name
                                            .build())
                                    .build())
                            .build())
                    .toList();

            // Create session parameters
            SessionCreateParams params = SessionCreateParams.builder()
                    .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl(successUrl)
                    .setCancelUrl(cancelUrl)
                    .addAllLineItem(lineItems)  // Add the prepared list of line items
                    .build();

            // Create the session
            Session session = Session.create(params);

            // Build and return the payment response
            PaymentResponse res = new PaymentResponse();
            res.setPayment_url(session.getUrl());
            return res;

        } catch (StripeException ex) {
            throw new IllegalStateException("Payment link creation failed: " + ex.getMessage(), ex);
        }
    }
}
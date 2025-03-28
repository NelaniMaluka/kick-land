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
            res.setSession_id(session.getId());
            return res;

        } catch (StripeException ex) {
            throw new IllegalStateException("Payment link creation failed.");
        }
    }

    public boolean verifyPayment(String sessionId) throws StripeException {
        try {
            Stripe.apiKey = stripeSecretKey;

            // Trim and validate that the sessionId is appropriate for test mode
            if (sessionId == null || !sessionId.trim().startsWith("cs_test_")) {
                throw new IllegalArgumentException("Invalid test session ID." + sessionId);
            }

            // Clean up sessionId to avoid accidental issues with leading/trailing spaces
            String cleanedSessionId = sessionId.trim();

            // Retrieve the session details from Stripe using the cleaned sessionId
            Session session = Session.retrieve(cleanedSessionId);

            // Check the payment status from the session's payment_status attribute
            return "paid".equals(session.getPaymentStatus());  // Returns true if the payment is successful
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Validation error: " + ex.getMessage(), ex);
        } catch (StripeException ex) {
            throw new RuntimeException("Error verifying the payment: " + ex.getMessage() + sessionId, ex);
        }
    }

}

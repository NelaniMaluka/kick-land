package com.examplekicklaandwebsite.KickLaand.response;

import lombok.Data;

@Data
public class PaymentResponse {
    private String payment_url;
    private String Session_id;
}

package com.examplekicklaandwebsite.KickLaand.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private String payment_url;
    private String Session_id;
}

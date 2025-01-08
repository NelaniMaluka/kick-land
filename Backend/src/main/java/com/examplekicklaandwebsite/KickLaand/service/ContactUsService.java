package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.model.ContactUs;
import org.springframework.http.ResponseEntity;

public interface ContactUsService {
    ResponseEntity<?> sendInfo(ContactUs contactUs) throws Exception;
}


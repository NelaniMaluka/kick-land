package com.examplekicklaandwebsite.KickLaand.service;

import com.examplekicklaandwebsite.KickLaand.model.Newsletter;
import org.springframework.http.ResponseEntity;

public interface NewsletterService {
    ResponseEntity<String> addNewsletter(Newsletter newsletter) throws Exception;
}



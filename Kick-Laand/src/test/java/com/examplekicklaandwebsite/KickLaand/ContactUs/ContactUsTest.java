package com.examplekicklaandwebsite.KickLaand.ContactUs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.mockito.ArgumentMatchers.anyString;

import com.examplekicklaandwebsite.KickLaand.Newsletter.NewsletterRepository;

@ExtendWith(MockitoExtension.class)
class ContactUsTest {

	@Mock
	ContactUsRepository contactUsRepositoryMock;
	
	@Mock
	NewsletterRepository newsletterRepositoryMock;
	
	@InjectMocks
	private ContactUsController contactUsController;

	@ParameterizedTest
	@CsvSource({
	    "Testname, Testemail@gmail.com, +278765432109, This is a test",
	    "null, Testemail@gmail.com, +278765432109, This is a test",
	    "TestName, null, +278765432109, This is a test",
	    "TestName, Testemail@gmail.com, null, This is a test",
	    "TestName, Testemail@gmail.com, +278765432109, null",
	})
	void sendContactDetails_VaryingInputs(String name, String email, String phoneNumber, String message) {
	    ContactUs contactUs = new ContactUs(1, name, email, phoneNumber, message);
	    lenient().when(newsletterRepositoryMock.findByEmail(email)).thenReturn(null);
	    ResponseEntity<?> responseEntity = contactUsController.sendInfo(contactUs);

	    if (name != null && email != null && phoneNumber != null && message != null) {
	        // Successful case: all fields are present
	        assertEquals(ResponseEntity.ok("We Received your message"), responseEntity);
	    } else {
	        // Missing required fields case: expect a 400 Bad Request response
	        assertEquals(ResponseEntity.badRequest().body("Email, Phone Number, and Message are Required"), responseEntity);
	    }
	}




}

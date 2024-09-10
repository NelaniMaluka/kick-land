package com.examplekicklaandwebsite.KickLaand.ContactUs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.examplekicklaandwebsite.KickLaand.Newsletter.NewsletterRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
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
	void sendContactDetails_VaryingInputsWithNull(String name, String email, String phoneNumber, String message) {
	    ContactUs contactUs = new ContactUs(name, email, phoneNumber, message);
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
	
	@ParameterizedTest
	@CsvSource({
	    "TestName, w, +278765432109, This is a test",
	    "TestName, Testemail@gmail.com, Wrong_Input, This is a test",
	})
	void sendContactDetails_VaryingInputsWithWrongData(String name, String email, String phoneNumber, String message) {
	    ContactUs contactUs = new ContactUs(name, email, phoneNumber, message);

	    // Mock behavior
	    when(newsletterRepositoryMock.findByEmail(email)).thenReturn(null);

	    // Call the method with the BindingResult parameter
	    ResponseEntity<?> responseEntity = contactUsController.sendInfo(contactUs );

	    System.out.println("Response Status: " + responseEntity.getStatusCode());
	    System.out.println("Response Body: " + responseEntity.getBody());
	    
	    assertEquals(ResponseEntity.badRequest().body("Invalid Credentials"), responseEntity); 
	}
}

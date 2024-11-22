package com.examplekicklaandwebsite.KickLaand.ContactUs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ContactUsTest {

    @Mock
    ContactUsService contactUsServiceMock;

    
    @ParameterizedTest
    @CsvSource({
    	"TestName, Testemail@gmail.com, +27876543209, This is a test", // valid 
        "'', Testemail@gmail.com, +27876543209, This is a test", // Missing name
        "TestName, '', +278765432109, This is a test", // Missing email
        "TestName, Testemail@gmail.com, '', This is a test", // Missing phone number
        "TestName, Testemail@gmail.com, +27876543209, ''", // Missing message
    })
    void sendContactDetails_VaryingInputsWithNull_WithValidInput(String name, String email, String phoneNumber, String message) throws Exception {
        // Creating ContactUs object based on input, treating empty strings as null
        ContactUs contactUs = new ContactUs(
                name.isEmpty() ? null : name,
                email.isEmpty() ? null : email,
                phoneNumber.isEmpty() ? null : phoneNumber,
                message.isEmpty() ? null : message
        );

        // Mocking the service method to simulate different behavior based on missing fields
        when(contactUsServiceMock.sendInfo(contactUs)).thenAnswer(invocation -> {
            ContactUs contact = invocation.getArgument(0);
            
            // Check for missing fields: null or empty string is considered missing
            if ( contact.getEmail() == null || contact.getPhoneNumber() == null || contact.getMessage() == null) {
                return "Email, Phone Number, and Message are Required"; // Return error if any field is missing
            }
            
            return "We Received your message"; // Return success message if no field is missing
        });

        String response = contactUsServiceMock.sendInfo(contactUs);

        // Assert that error message is returned when any required field is missing
        if ( email == null || phoneNumber == null || message == null || name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || message.isEmpty()) {
            assertEquals("Email, Phone Number, and Message are Required", response); // Missing required field
        } else {
            assertNotEquals("Email, Phone Number, and Message are Required", response); // No field missing, should not return error message
            assertEquals("We Received your message", response); // Expect success message for valid data
        }
    }


    // Test for incorrect inputs, like wrong email format or phone number format
    @ParameterizedTest
    @CsvSource({
        "TestName, w, +278765432109, This is a test", // Invalid email
        "TestName, Testemail@gmail.com, Wrong_Input, This is a test", // Invalid phone number
    })
    void sendContactDetails_VaryingInputsWithWrongInput_WithValidInput(String name, String email, String phoneNumber, String message) throws Exception {
        ContactUs contactUs = new ContactUs(name, email, phoneNumber, message);
        
        // Mocking the service method to return an error message for invalid email or phone number
        when(contactUsServiceMock.sendInfo(contactUs)).thenAnswer(invocation -> {
        	ContactUs contact = invocation.getArgument(0);
        	
        	if (!isValidEmail(contact.getEmail()) || !isValidPhoneNumber(contact.getPhoneNumber())) {
        		return "Invalid Credentials";
        	}
        	
        	return "We Received your message";
        	
        });

        String response = contactUsServiceMock.sendInfo(contactUs);

        // Check for invalid data, expecting a bad request with specific message
        assertEquals("Invalid Credentials", response); // Invalid credentials message
    }
    
    private boolean isValidEmail(String email) {
    	if (email == null) {
    		return false;
    	}
    	// regex pattern for email validation
    	String emailRegex = "^[\\\\w.-]+@[\\\\w.-]+\\\\.[a-zA-Z]{2,}$";
    	return email.matches(emailRegex);
    }
    
    private boolean isValidPhoneNumber(String phoneNumber) {
    	if (phoneNumber == null) {
    		return false;
    	}
    	// regex pattern for email validation
    	String saPhoneRegex = "^(?:\\+27|0)(?:(?:[1-9][0-9]{1}\\s?[0-9]{3}\\s?[0-9]{4})|(?:\\d{9}))$";
    	return phoneNumber.matches(saPhoneRegex);
    }

}

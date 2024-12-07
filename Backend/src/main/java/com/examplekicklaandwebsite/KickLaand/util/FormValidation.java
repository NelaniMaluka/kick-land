package com.examplekicklaandwebsite.KickLaand.util;

public class FormValidation {
	
	private FormValidation() {
		
	}

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";
        return email.matches(emailRegex);
    }

    public static boolean isValidPhonenumber(String phonenumber) {
    	phonenumber = phonenumber.trim();
        String phonenumberRegex = "^[0-9]{10,15}$";
        return phonenumber.matches(phonenumberRegex);
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
        return password.matches(passwordRegex);
    }

    public static String toNullIfEmpty(String value) {
        return (value == null || value.trim().isEmpty()) ? null : value;
    }
	
}

export const isValidEmail = (email) => {
  // Basic email format validation
  const emailRegex = /\S+@\S+\.\S+/;
  return emailRegex.test(email);
};

export const isValidOtp = (otp) => {
  // Check if the OTP is a 6-digit number
  const otpRegex = /^\d{6}$/;
  return otpRegex.test(otp);
};

export const isValidPassword = (password) => {
  // Password validation regex: at least one special character, one capital letter, one number, and minimum 8 characters
  const passwordRegex = /^(?=.*[!@#$%^&*])(?=.*[A-Z])(?=.*[0-9]).{8,}$/;
  return passwordRegex.test(password);
};

export const isValidPhoneNumber = (phoneNumber) => {
  // Phone number validation regex for South African phone numbers
  const phoneNumberRegex = /^(\+27|0)(\d{9})$/;
  return phoneNumberRegex.test(phoneNumber);
};

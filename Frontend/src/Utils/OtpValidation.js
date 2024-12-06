export default function isValidOtp(otp) {
  // Check if the OTP is a 6-digit number
  const otpRegex = /^\d{6}$/;
  return otpRegex.test(otp);
}

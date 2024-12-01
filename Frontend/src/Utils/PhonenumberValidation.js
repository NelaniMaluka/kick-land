export default function isValidPhoneNumber(phoneNumber) {
  // Phone number validation regex for South African phone numbers
  const phoneNumberRegex = /^(\+27|0)(\d{9})$/;
  return phoneNumberRegex.test(phoneNumber);
}

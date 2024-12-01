export default function isValidEmail(email) {
  // Basic email format validation
  const emailRegex = /\S+@\S+\.\S+/;
  return emailRegex.test(email);
}

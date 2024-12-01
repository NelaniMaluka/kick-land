export default function isValidPassword(password) {
  // Password validation regex: at least one special character, one capital letter, one number, and minimum 8 characters
  const passwordRegex = /^(?=.*[!@#$%^&*])(?=.*[A-Z])(?=.*[0-9]).{8,}$/;
  return passwordRegex.test(password);
}

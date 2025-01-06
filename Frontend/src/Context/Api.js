import axios from "axios";

export const url = "http://localhost:8080";

export const apiClient = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
});

// Newsletter API call
export function SignForNewsletter(email) {
  return apiClient.post("/newsletter", { email });
}

//Contact Us API call
export function contactUs(name, email, phoneNumber, message) {
  return apiClient.post("/contact-us", {
    name,
    email,
    phoneNumber,
    message,
  });
}

//Products API Call
export function RetrieveProducts() {
  return apiClient.get("/products");
}

//Forgot Password API calls
export function ForgotPassword(email) {
  return apiClient.post("/forgot-password/verify-mail", { email });
}
export function VerifyOtp(otp, email) {
  return apiClient.post("/forgot-password/verify-otp", { otp, email });
}

export function ChangePassword(password, repeatPassword, email) {
  return apiClient.post("/forgot-password/change-password", {
    password,
    repeatPassword,
    email,
  });
}

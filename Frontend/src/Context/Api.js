import axios from "axios";

export const url = "https://kick-land.onrender.com";

export const apiClient = axios.create({
  baseURL: "https://kick-land.onrender.com",
  headers: {
    "Content-Type": "application/json",
  },
});

// Newsletter API call// Api.js
export async function SignForNewsletter(email) {
  try {
    // Send POST request using apiClient
    const response = await apiClient.post("/newsletter", { email });

    // If request is successful (status code 2xx)
    if (response.status >= 200 && response.status < 300) {
      return {
        status: response.status,
        data: response.data,
      };
    }

    // Handle case when the response status isn't successful
    throw new Error(response.data || "Something went wrong");
  } catch (error) {
    // Handle both fetch errors and backend error response
    return {
      status: error.response?.status || 500, // Use status from error if it exists
      message: error.message || "An unexpected error occurred while signing up",
    };
  }
}

//Contact Us API call
export async function contactUs(name, email, phoneNumber, message) {
  try {
    const response = await apiClient.post("/contact-us", {
      name,
      email,
      phoneNumber,
      message,
    });

    // If request is successful (status code 2xx)
    if (response.status >= 200 && response.status < 300) {
      return {
        status: response.status,
        data: response.data,
      };
    }

    // Handle case when the response status isn't successful
    throw new Error(response.data || "Something went wrong");
  } catch (error) {
    // Handle both fetch errors and backend error response
    return {
      status: error.response?.status || 500, // Use status from error if it exists
      message: error.message || "An unexpected error occurred while signing up",
    };
  }
}

//Products API Call
export async function RetrieveProducts() {
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

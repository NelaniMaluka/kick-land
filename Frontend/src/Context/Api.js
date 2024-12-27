import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
});

// User API Calls
export function CreateAccount(firstname, lastname, email, password) {
  return apiClient.post("/api/user/create-account", {
    firstname,
    lastname,
    email,
    password,
  });
}
export function LogIn(email, password) {
  return apiClient.post("/api/user/login", {
    email,
    password,
  });
}
export function UpdateUserDetails(
  userId,
  firstname,
  lastname,
  email,
  phonenumber,
  address
) {
  return apiClient.put(`/api/user/update-user/${userId}`, {
    firstname,
    lastname,
    email,
    phonenumber,
    address,
  });
}

//User Cart API calls
export function GetUserCart(email) {
  return apiClient.get(`/api/cart?email=${email}`);
}
export function AddToCart(cartProduct) {
  return apiClient.post("/api/cart", cartProduct);
}
export function UpdateCartItem(userId, productId, productQuantity) {
  const parsedUserId = parseInt(userId, 10);
  const parsedProductId = parseInt(productId, 10);
  const parsedproductQuantity = parseInt(productQuantity, 10);

  return apiClient.put(
    `/api/cart?userId=${parsedUserId}&productId=${parsedProductId}&productQuantity=${parsedproductQuantity}`
  );
}
export function DeleteCartItem(userId, productId) {
  const parsedUserId = parseInt(userId, 10);
  const parsedProductId = parseInt(productId, 10);

  return apiClient.delete(
    `/api/cart?userId=${parsedUserId}&productId=${parsedProductId}`
  );
}

// Newsletter API call
export function SignForNewsletter(email) {
  return apiClient.post("/api/newsletter", { email });
}

//Contact Us API call
export function ContactUs(name, email, phoneNumber, message) {
  return apiClient.post("/api/contact-us", {
    name,
    email,
    phoneNumber,
    message,
  });
}

//Products API Call
export function RetrieveProducts() {
  return apiClient.get("/api/products");
}

//User Orders API calls
export function GetUserOrders(userId) {
  return apiClient.get(`/api/order?userId=${userId}`);
}
export function AddToOrders(
  userId,
  firstname,
  lastname,
  email,
  phoneNumber,
  province,
  ZIPCode,
  address
) {
  return apiClient.post("/api/order", {
    userId,
    firstname,
    lastname,
    email,
    phoneNumber,
    province,
    ZIPCode,
    address,
  });
}

//Forgot Password API calls
export function ForgotPassword(email) {
  return apiClient.post("/api/forgot-password/verify-mail", { email });
}
export function VerifyOtp(otp, email) {
  return apiClient.post("/api/forgot-password/verify-otp", { otp, email });
}

export function ChangePassword(password, repeatPassword, email) {
  return apiClient.post("/api/forgot-password/change-password", {
    password,
    repeatPassword,
    email,
  });
}

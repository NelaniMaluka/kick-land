import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
});

export function LogIn(email, password) {
  return apiClient.post("/Backend/Login", {
    email,
    password,
  });
}

export function CreateAccount(username, surname, email, password) {
  return apiClient.post("/Backend/Create-Account", {
    username,
    surname,
    email,
    password,
  });
}

export function SignForNewsletter(email) {
  return apiClient.post("/Backend/Newsletter", { email });
}

export function ContactUs(name, email, phoneNumber, message) {
  return apiClient.post("/Backend/ContactUs", {
    name,
    email,
    phoneNumber,
    message,
  });
}

export function RetrieveProducts() {
  return apiClient.get("/Backend/Products");
}

export function GetUserCart(email) {
  return apiClient.get(`/Backend/Cart?email=${email}`);
}

export function AddToCart(productWithUserId) {
  return apiClient.post("/Backend/Cart", productWithUserId);
}

export function DeleteCartItem(userId, productId) {
  const parsedUserId = parseInt(userId, 10);
  const parsedProductId = parseInt(productId, 10);

  return apiClient.delete(
    `/Backend/Cart?userId=${parsedUserId}&productId=${parsedProductId}`
  );
}

export function UpdateUserDetails(
  userId,
  username,
  surname,
  email,
  phonenumber // Fix: Correct field name
) {
  return apiClient.put(`/Backend/Update-User/${userId}`, {
    username,
    surname,
    email,
    phonenumber, // Fix: Correct field name
  });
}

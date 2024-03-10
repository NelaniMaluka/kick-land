import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080",
});

export function LogIn(email, password) {
  return apiClient.post("/api/user/login", {
    email,
    password,
  });
}

export function CreateAccount(username, surname, email, password) {
  return apiClient.post("/api/user/create-account", {
    username,
    surname,
    email,
    password,
  });
}

export function SignForNewsletter(email) {
  return apiClient.post("/api/public/newsletter", { email });
}

export function ContactUs(name, email, phoneNumber, message) {
  return apiClient.post("/api/public/contactUs", {
    name,
    email,
    phoneNumber,
    message,
  });
}

export function RetrieveProducts() {
  return apiClient.get("/api/public/products");
}

export function GetUserCart(email) {
  return apiClient.get(`/api/user/cart?email=${email}`);
}

export function AddToCart(productWithUserId) {
  return apiClient.post("/api/user/cart", productWithUserId);
}

export function DeleteCartItem(userId, productId) {
  const parsedUserId = parseInt(userId, 10);
  const parsedProductId = parseInt(productId, 10);

  return apiClient.delete(
    `/api/user/cart?userId=${parsedUserId}&productId=${parsedProductId}`
  );
}

export function UpdateCartItem(userId, productId, productQuantity) {
  const parsedUserId = parseInt(userId, 10);
  const parsedProductId = parseInt(productId, 10);
  const parsedproductQuantity = parseInt(productQuantity, 10);

  return apiClient.put(
    `/api/user/cart?userId=${parsedUserId}&productId=${parsedProductId}&productQuantity=${parsedproductQuantity}`
  );
}

export function UpdateUserDetails(
  userId,
  username,
  surname,
  email,
  phonenumber,
  address
) {
  return apiClient.put(`/api/user/update-user/${userId}`, {
    username,
    surname,
    email,
    phonenumber,
    address,
  });
}

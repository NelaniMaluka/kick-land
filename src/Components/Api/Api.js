import axios from "axios";
import { useAuth } from "../Security/AuthContext";
import { useEffect } from "react";

function Api() {
  const authContext = useAuth();

  const apiClient = axios.create({
    baseURL: "http://localhost:8080",
  });

  async function Login(email, password) {
    try {
      const response = await apiClient.post("/Backend/Login", {
        email,
        password,
      });
      authContext.Login(response); // Assuming user details are in response.data
      return { success: true, response };
    } catch (error) {
      authContext.Logout();
      return { success: false, error };
    }
  }

  async function CreateAccount(username, surname, email, password) {
    try {
      const response = await apiClient.post("/Backend/Create-Account", {
        username,
        surname,
        email,
        password,
      });
      authContext.Login(response);
      console.log("success:" + true, response);
      return { success: true, response };
    } catch (error) {
      authContext.Logout();
      console.log("success:" + true, error);
      return { success: false, error };
    }
  }

  async function SignForNewsletter(email) {
    try {
      const response = await apiClient.post("/Backend/Newsletter", { email });
      return { success: true, response };
    } catch (error) {
      return { success: false, error };
    }
  }

  async function ContactUs(name, email, phoneNumber, message) {
    try {
      const response = await apiClient.post("/Backend/ContactUs", {
        name,
        email,
        phoneNumber,
        message,
      });
      return { success: true, response };
    } catch (error) {
      return { success: false, error };
    }
  }

  useEffect(() => {
    retrieveProducts();
  }, []);

  async function retrieveProducts() {
    try {
      const response = await apiClient.get("/Backend/Products");
      authContext.Products(response.data);
    } catch (error) {
      authContext.Products("");
    }
  }

  async function addToCart(productWithUserId) {
    try {
      const response = await apiClient.post("/Backend/Cart", productWithUserId);
      authContext.CartItems(response.data);
    } catch (error) {
      console.log(error);
    }
  }

  // Return the API functions
  return {
    Login,
    CreateAccount,
    SignForNewsletter,
    ContactUs,
    addToCart,
  };
}

export default Api;

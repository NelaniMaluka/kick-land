// Api.js

import axios from "axios";
import { useAuth } from "../Security/AuthContext";

function Api() {
  const authContext = useAuth();

  const apiClient = axios.create({
    baseURL: "http://localhost:8080",
  });

  function Login(email, password) {
    return apiClient
      .post("/Backend/Login", { email, password })
      .then(function (response) {
        authContext.Login(response);
        return { success: true, response };
      })
      .catch(function (error) {
        authContext.Logout();
        return { success: false, error };
      });
  }

  function CreateAccount(username, surname, email, password) {
    return apiClient
      .post("/Backend/Create-Account", {
        username,
        surname,
        email,
        password,
      })
      .then(function (response) {
        authContext.Login(response);
        return { success: true, response };
      })
      .catch(function (error) {
        authContext.Logout();
        return { success: false, error };
      });
  }

  function SignForNewsletter(email) {
    return apiClient
      .post("/Backend/Newsletter", { email })
      .then(function (response) {
        return { success: true, response };
      })
      .catch(function (error) {
        return { success: false, error };
      });
  }

  function ContactUs(name, email, phoneNumber, message) {
    return apiClient
      .post("/Backend/ContactUs", {
        name,
        email,
        phoneNumber,
        message,
      })
      .then(function (response) {
        return { success: true, response };
      })
      .catch(function (error) {
        return { success: false, error };
      });
  }

  // Return the API functions
  return {
    Login,
    CreateAccount,
    SignForNewsletter,
    ContactUs,
  };
}

export default Api;

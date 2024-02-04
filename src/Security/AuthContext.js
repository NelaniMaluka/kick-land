import { createContext, useState, useContext } from "react";
import axios from "axios";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

function AuthProvider({ children }) {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [isUsername, setUsername] = useState(false);

  function Login(email, password) {
    return axios
      .post("http://localhost:8080/Backend/Login", { email, password })
      .then(function (response) {
        // Assuming the response contains authentication information
        setAuthenticated(response.data);
        return response;
      });
  }

  function CreateAccount(username, surname, email, password) {
    return axios
      .post("http://localhost:8080/Backend/Create-Account", {
        username,
        surname,
        email,
        password,
      })
      .then(function (response) {
        // Assuming the response contains authentication information
        setAuthenticated(response);
        return response;
      });
    // No catch block here, let errors propagate
  }

  function Logout() {
    setAuthenticated(false);
  }

  function SignForNewsletter(email) {
    return axios
      .post("http://localhost:8080/Backend/Newsletter", { email })
      .then(function (response) {
        console.log(response);
        return response;
      });
  }

  function ContactUs(name, email, phoneNumber, message) {
    return axios
      .post("http://localhost:8080/Backend/ContactUs", {
        name,
        email,
        phoneNumber,
        message,
      })
      .then(function (response) {
        return response;
      });
  }

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        Login,
        Logout,
        CreateAccount,
        SignForNewsletter,
        ContactUs,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;

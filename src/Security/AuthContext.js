import { createContext, useState, useContext } from "react";
import axios from "axios";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

function AuthProvider({ children }) {
  const [isAuthenticated, setAuthenticated] = useState(false);

  function Login(email, password) {
    return axios
      .post("http://localhost:8080/Backend/Login", { email, password })
      .then(function (response) {
        // Assuming the response contains authentication information
        setAuthenticated(response.data);
        return response.data;
      })
      .catch(function (error) {
        setAuthenticated(false);
        return error;
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
        setAuthenticated(response.data);
        return response.data;
      })
      .catch(function (error) {
        setAuthenticated(false);
        return error.response.data;
      });
  }

  function Logout() {
    setAuthenticated(false);
  }

  function SignForNewsletter(email) {
    return axios
      .post("http://localhost:8080/Backend/Newsletter", { email })
      .then(function (response) {
        return response.data;
      })
      .catch(function (error) {
        return error.response.data;
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
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;

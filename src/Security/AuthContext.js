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
        console.log(response.data);
        setAuthenticated(response.data);
        return response.data;
      })
      .catch(function (error) {
        setAuthenticated(false);
        return error.response.data;
      });
  }

  function CreateAccount(username, password, email, surname) {
    if (username === "username" && password === "password") {
      setAuthenticated(true);
      return true;
    } else {
      setAuthenticated(false);
      return false;
    }
  }

  function Logout() {
    setAuthenticated(false);
  }

  return (
    <AuthContext.Provider value={{ isAuthenticated, Login, Logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;

import { createContext, useState, useContext } from "react";
import {
  RetrieveProducts,
  ForgotPassword,
  VerifyOtp,
  ChangePassword,
} from "./Api";
import { useEffect } from "react";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

function AuthProvider({ children }) {
  const [isProducts, setProducts] = useState([]);
  const [isForgotPasswordEmail, setForgotPasswordEmail] = useState([]);
  const [cartC, setCart] = useState([]);

  useEffect(() => {
    // Fetch products only when the component mounts
    retrieveProducts();
  }, []);

  //retrieve products function
  async function retrieveProducts() {
    try {
      const response = await RetrieveProducts();
      if (response.status === 200) {
        setProducts(response.data);
      } else {
      }
    } catch (e) {
      console.log(e);
    }
  }

  //Forgot Password API methods
  async function forgotPassword(email) {
    try {
      const response = await ForgotPassword(email); // API call

      if (response.status === 200) {
        setForgotPasswordEmail(email);
        return { success: true, response };
      } else {
        return {
          success: false,
          status: response.status,
          message: response.data?.message || "Unexpected error occurred",
        };
      }
    } catch (e) {
      return {
        success: false,
        status: e.response?.status || 500,
        message:
          e.response?.data?.message ||
          "Internal Server Error. Please try again later.",
      };
    }
  }

  async function verifyOtp(otp) {
    try {
      const response = await VerifyOtp(otp, isForgotPasswordEmail); // API call
      if (response.status === 200) {
        return { success: true, response };
      } else {
        return {
          success: false,
          status: response.status,
          message: response.data?.message || "Unexpected error occurred",
        };
      }
    } catch (e) {
      return {
        success: false,
        status: e.response?.status || 500,
        message:
          e.response?.data?.message ||
          "Internal Server Error. Please try again later.",
      };
    }
  }

  async function changePassword(password, repeatPassword) {
    try {
      const response = await ChangePassword(
        password,
        repeatPassword,
        isForgotPasswordEmail
      );

      if (response.status === 200) {
        return { success: true, response };
      } else {
        return {
          success: false,
          status: response.status,
          message: response.data?.message || "Unexpected error occurred",
        };
      }
    } catch (e) {
      return {
        success: false,
        status: e.response?.status || 500,
        message:
          e.response?.data?.message ||
          "Internal Server Error. Please try again later.",
      };
    }
  }

  return (
    <AuthContext.Provider
      value={{
        isProducts,
        retrieveProducts,
        forgotPassword,
        verifyOtp,
        changePassword,
        cartC,
        setCart,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;

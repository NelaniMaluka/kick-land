import { createContext, useState, useContext } from "react";
import {
  LogIn,
  CreateAccount,
  SignForNewsletter,
  ContactUs,
  RetrieveProducts,
  AddToCart,
  GetUserCart,
  UpdateUserDetails,
  DeleteCartItem,
  UpdateCartItem,
  AddToOrders,
  GetUserOrders,
  ForgotPassword,
} from "../Api/Api";
import { useEffect } from "react";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

function AuthProvider({ children }) {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [isUser, setUser] = useState(null);
  const [isProducts, setProducts] = useState([]);
  const [isCartItems, setCartItems] = useState([]);
  const [isUserOrders, setUserOrders] = useState([]);

  async function login(email, password) {
    try {
      const response = await LogIn(email, password);
      if (response.status === 200) {
        setUser(response.data);
        setAuthenticated(true);
        getUserCart(response.data.email);
        getUserOrders(response.data.email);
        return { success: true, response };
      } else {
        logout();
        return { success: false, response };
      }
    } catch (e) {
      logout();
      return { success: false, response: e.response };
    }
  }

  function logout() {
    setAuthenticated(false);
    setUser(null);
    setCartItems([]);
  }

  async function createAccount(username, surname, email, password) {
    try {
      const response = await CreateAccount(username, surname, email, password);
      if (response.status === 200) {
        setUser(response.data);
        setAuthenticated(true);
        return { success: true, response };
      } else {
        logout();
        return { success: false, response };
      }
    } catch (e) {
      logout();
      return { success: false, response: e.response };
    }
  }

  async function signUpForNewsletter(email) {
    try {
      const response = await SignForNewsletter(email);
      return { success: response.status === 200, response };
    } catch (e) {
      return { success: false, response: e.response };
    }
  }

  async function contactUs(name, email, phoneNumber, message) {
    try {
      const response = await ContactUs(name, email, phoneNumber, message);
      return { success: response.status === 200, response };
    } catch (e) {
      return { success: false, response: e.response };
    }
  }

  useEffect(() => {
    // Fetch products only when the component mounts
    retrieveProducts();
  }, []);

  async function retrieveProducts() {
    try {
      const response = await RetrieveProducts();
      if (response.status === 200) {
        setProducts(response.data);
      } else {
      }
    } catch (e) {}
  }

  async function getUserCart(email) {
    try {
      const response = await GetUserCart(email);
      if (response.status === 200) {
        setCartItems(response.data);
      } else {
        setCartItems(null);
      }
    } catch (e) {
      setCartItems(null);
    }
  }

  async function addToCart(productWithUserId) {
    try {
      const response = await AddToCart(productWithUserId);
      if (response.status === 200) {
        setCartItems(response.data);
        return true;
      } else {
        setCartItems([]);
        return false;
      }
    } catch (e) {
      setCartItems([]);
    }
  }

  async function updateCartItem(userId, productId, productQuantity) {
    try {
      const response = await UpdateCartItem(userId, productId, productQuantity);
      console.log(response);
      if (response.status === 200) {
        setCartItems(response.data);
        return { success: true, response: response };
      } else {
        return response;
      }
    } catch (e) {
      return e;
    }
  }

  async function deleteCartItem(userId, productId) {
    try {
      const response = await DeleteCartItem(userId, productId);
      if (response.status === 200) {
        setCartItems(response.data);
        return { success: true, response: response };
      } else {
        return response;
      }
    } catch (e) {
      return e;
    }
  }

  async function getUserOrders(email) {
    try {
      const response = await GetUserOrders(email);
      if (response.status === 200) {
        setUserOrders(response.data);
      } else {
        setUserOrders(null);
      }
    } catch (e) {
      setUserOrders(null);
    }
  }

  async function addToOrders(orders) {
    console.log(orders);
    try {
      const response = await AddToOrders(orders);
      if (response.status === 200) {
        setUserOrders(response.data);
        return true;
      } else {
        setUserOrders([]);
        return false;
      }
    } catch (e) {
      setUserOrders([]);
    }
  }

  async function updateUserDetails(
    userId,
    username,
    surname,
    email,
    phonenumber,
    address
  ) {
    try {
      const response = await UpdateUserDetails(
        userId,
        username,
        surname,
        email,
        phonenumber,
        address
      );
      if (response.status === 200) {
        setUser(response.data);
        setAuthenticated(true);
        getUserCart(response.data.email);
        return { success: true, response };
      } else {
        return { success: false, response };
      }
    } catch (e) {
      return { success: false, response: e.response };
    }
  }

  async function forgotPassword(email) {
    try {
      console.log(email);
      const response = await ForgotPassword(email);

      if (response.status === 200) {
        return true;
      } else {
        return false;
      }
    } catch (e) {
      return false;
    }
  }

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        isUser,
        login,
        logout,
        isProducts,
        isCartItems,
        updateCartItem,
        deleteCartItem,
        createAccount,
        signUpForNewsletter,
        contactUs,
        addToCart,
        updateUserDetails,
        retrieveProducts,
        isUserOrders,
        addToOrders,
        forgotPassword,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;

import { createContext, useState, useContext } from "react";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

function AuthProvider({ children }) {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [isUser, setUser] = useState(null);
  const [isProducts, setProducts] = useState(null);
  const [isCartItems, setCartItems] = useState(0);

  function Login(user) {
    setUser(user.data);
    console.log(user.data);
    setAuthenticated(true);
  }

  function Logout() {
    setAuthenticated(false);
    setUser("");
  }

  function Products(products) {
    setProducts(products);
  }

  function CartItems(items) {
    setCartItems(items);
  }

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        isUser,
        Login,
        Logout,
        isProducts,
        Products,
        CartItems,
        isCartItems,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;

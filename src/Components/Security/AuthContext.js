import { createContext, useState, useContext } from "react";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

function AuthProvider({ children }) {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [isUser, setUser] = useState("me");
  const [isproducts, setProducts] = useState("products");

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

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        isUser,
        Login,
        Logout,
        isproducts,
        Products,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;

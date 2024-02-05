import { createContext, useState, useContext } from "react";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

function AuthProvider({ children }) {
  const [isAuthenticated, setAuthenticated] = useState(false);
  const [isUser, setUser] = useState("me");

  function Login(user) {
    setUser(user.data);
    console.log(user.data);
    setAuthenticated(true);
  }

  function Logout() {
    setAuthenticated(false);
  }

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        isUser,
        Login,
        Logout,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export default AuthProvider;

import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import NavBar from "./Components/NavBar/NavBar";
import Footer from "./Components/Footer/Footer";
import HomePage from "./Components/HomePage/HomePage";
import Shop from "./Components/Shop/Shop";
import Info from "./Components/Info/Info";
import AuthProvider from "./Security/AuthContext";
import LoginForm from "./Components/LoginLogout/LogInForm";
import Cart from "./Components/Cart/Cart";
import Favourites from "./Components/Favourites/Favourites";
import { useAuth } from "./Security/AuthContext";

function AuthenticatedRoute({ children }) {
  const authContext = useAuth();
  if (authContext.isLoggedIn) {
    return children;
  }
  return <Navigate to="/Login" />;
}

function App() {
  return (
    <div className="App">
      <AuthProvider>
        <BrowserRouter>
          <NavBar />
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/Shop/:item" element={<Shop />} />
            <Route path="/Info/:page" element={<Info />} />
            <Route path="/Login" element={<LoginForm />} />
            <Route
              path="/Cart"
              element={
                <AuthenticatedRoute>
                  {" "}
                  <Cart />
                </AuthenticatedRoute>
              }
            />
            <Route
              path="/Favourites"
              element={
                <AuthenticatedRoute>
                  {" "}
                  <Favourites />
                </AuthenticatedRoute>
              }
            />
          </Routes>
          <Footer />
        </BrowserRouter>
      </AuthProvider>
    </div>
  );
}

export default App;

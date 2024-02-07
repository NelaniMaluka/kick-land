import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import NavBar from "./Components/NavBar/NavBar";
import Footer from "./Components/Footer/Footer";
import HomePage from "./Components/HomePage/HomePage";
import Shop from "./Components/Shop/Shop";
import AuthProvider from "./Components/Security/AuthContext";
import LoginForm from "./Components/LoginLogout/LogInForm";
import Cart from "./Components/Cart/Cart";
import Favourites from "./Components/Favourites/Favourites";
import { useAuth } from "./Components/Security/AuthContext";
import AboutUs from "./Components/Info/About-Us";
import TermsOfService from "./Components/Info/TermsOfService";
import RefundPolicy from "./Components/Info/RefundPolicy";
import CreateAccount from "./Components/LoginLogout/CreateAccount";
import ForgotPassword from "./Components/LoginLogout/ForgotPassword";
import Help from "./Components/LoginLogout/Help";
import ShopAll from "./Components/Shop/ShopAll/ShopAll";

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
            <Route path="/Login" element={<LoginForm />} />
            <Route path="/Create-Account" element={<CreateAccount />} />
            <Route path="/Forgot-Password" element={<ForgotPassword />} />
            <Route path="/Shop/:item" element={<Shop />} />
            <Route path="/Shop/Shop-All" element={<ShopAll />} />
            <Route path="/Info/About-Us" element={<AboutUs />} />
            <Route path="/Info/Terms-of-Service" element={<TermsOfService />} />
            <Route path="/Info/Refund-Policy" element={<RefundPolicy />} />
            <Route path="/Info/Help" element={<Help />} />
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

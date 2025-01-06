import "@stripe/stripe-js";
import React, { useEffect } from "react";
import { BrowserRouter, Routes, Route, useLocation } from "react-router-dom";
import NavBar from "./Components/NavBar/NavBar";
import Footer from "./Components/Footer/Footer";
import AuthProvider from "./Context/AuthContext";
import { PrimeReactProvider } from "primereact/api";
import HomePage from "./Pages/Home/HomePage";
import AuthRoutes from "./Routes/AuthRoutes";
import ForgotPasswordRoutes from "./Routes/ForgotPasswordRoutes";
import ShopRoutes from "./Routes/ShopRoutes";
import InfoRoutes from "./Routes/InfoRoutes";
import DashboardRoutes from "./Routes/DashboardRoutes";
import "./App.css";
import { useDispatch, useSelector } from "react-redux";
import { getUser } from "./State/Authentication/Action";
import { getOrder } from "./State/Order/Action";
import { getCart } from "./State/Cart/Action";

function ScrollToTop() {
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, [pathname]);

  return null;
}

function App() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const { auth } = useSelector((store) => store);

  useEffect(() => {
    if (jwt) {
      dispatch(getUser(auth.jwt || jwt));
      dispatch(getOrder(auth.jwt || jwt));
      dispatch(getCart(auth.jwt || jwt));
    }
  }, [auth.jwt]);

  return (
    <div className="App">
      <PrimeReactProvider>
        <AuthProvider>
          <BrowserRouter>
            <ScrollToTop />
            <NavBar className="navHeight" />
            <div className="content">
              <Routes>
                <Route path="/" element={<HomePage />} />
                {AuthRoutes()}
                {ForgotPasswordRoutes()}
                {ShopRoutes()}
                {InfoRoutes()}
                {DashboardRoutes()}
              </Routes>
              <Footer />
            </div>
          </BrowserRouter>
        </AuthProvider>
      </PrimeReactProvider>
    </div>
  );
}

export default App;

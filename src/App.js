import "@stripe/stripe-js";
import React, { useEffect } from "react";
import {
  BrowserRouter,
  Routes,
  Route,
  Navigate,
  useLocation,
} from "react-router-dom";
import NavBar from "./Components/NavBar/NavBar";
import Footer from "./Components/Footer/Footer";
import HomePage from "./Components/HomePage/HomePage";
import AuthProvider from "./Components/Security/AuthContext";
import LoginForm from "./Components/LoginLogout/LogInForm";
import { useAuth } from "./Components/Security/AuthContext";
import AboutUs from "./Components/Info/About-Us";
import TermsOfService from "./Components/Info/TermsOfService";
import RefundPolicy from "./Components/Info/RefundPolicy";
import CreateAccount from "./Components/LoginLogout/CreateAccount";
import ForgotPassword from "./Components/LoginLogout/ForgotPassword";
import Help from "./Components/LoginLogout/Help";
import ShopAll from "./Components/Shop/ShopAll/ShopAll";
import ShopByCategory from "./Components/Shop/ShopByCategory/ShopByCategory";
import { PrimeReactProvider } from "primereact/api";
import Alert from "./Components/LoginLogout/Alert";
import Dashboard from "./Components/Dashboard/Dashboard";
import ProductDetails from "./Components/Shop/ShopProduct/ProductDetails";

function AuthenticatedRoute({ children }) {
  const authContext = useAuth();
  if (authContext.isAuthenticated) {
    return children;
  }
  Alert({ message: "LogIn First" });
  return <Navigate to="/Login" />;
}

function ScrollToTop() {
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, [pathname]);

  return null;
}

function App() {
  return (
    <div className="App">
      <PrimeReactProvider>
        <AuthProvider>
          <BrowserRouter>
            <ScrollToTop />
            <NavBar />
            <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/Login" element={<LoginForm />} />
              <Route path="/Create-Account" element={<CreateAccount />} />
              <Route path="/Forgot-Password" element={<ForgotPassword />} />
              <Route path="/Shop/:item" element={<ShopByCategory />} />
              <Route
                path="/Shop/:category/:productName"
                element={<ProductDetails />}
              />
              <Route path="/Shop/Shop-All" element={<ShopAll />} />
              <Route path="/Info/About-Us" element={<AboutUs />} />
              <Route
                path="/Info/Terms-of-Service"
                element={<TermsOfService />}
              />
              <Route path="/Info/Refund-Policy" element={<RefundPolicy />} />
              <Route path="/Info/Help" element={<Help />} />
              <Route
                path="/Dashboard"
                element={
                  <AuthenticatedRoute>
                    <Dashboard />
                  </AuthenticatedRoute>
                }
              />
            </Routes>
            <Footer />
          </BrowserRouter>
        </AuthProvider>
      </PrimeReactProvider>
    </div>
  );
}

export default App;

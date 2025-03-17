import "@stripe/stripe-js";
import React, { useEffect } from "react";
import { Routes, Route, useLocation } from "react-router-dom";
import { createBrowserHistory } from "history";
import { unstable_HistoryRouter as HistoryRouter } from "react-router-dom";
import NavBar from "./Components/NavBar/NavBar";
import Footer from "./Components/Footer/Footer";
import AuthProvider from "./Context/AuthContext";
import { PrimeReactProvider } from "primereact/api";
import HomePage from "./Pages/Home/HomePage";
import PageNotFound from "./Pages/PageNotFound/PageNotFound";
import AuthRoutes from "./Routes/AuthRoutes";
import ForgotPasswordRoutes from "./Routes/ForgotPasswordRoutes";
import ShopRoutes from "./Routes/ShopRoutes";
import InfoRoutes from "./Routes/InfoRoutes";
import DashboardRoutes from "./Routes/DashboardRoutes";
import { useDispatch, useSelector } from "react-redux";
import { getUser } from "./State/Authentication/Action";
import { getOrder } from "./State/Order/Action";
import { getCart } from "./State/Cart/Action";
import "./App.css";

const history = createBrowserHistory();

const ScrollToTop = () => {
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, [pathname]);

  return null;
};

// Layout Component for pages with NavBar and Footer

const Layout = ({ children, noLayout }) => {
  if (noLayout) {
    return children; // Only render the children (PageNotFound without NavBar and Footer)
  }

  return (
    <>
      <NavBar className="navHeight" />
      <div className="content">{children}</div>
      <Footer />
    </>
  );
};

export default function App() {
  const dispatch = useDispatch();
  const jwt = localStorage.getItem("jwt");
  const auth = useSelector((state) => state.auth);

  useEffect(() => {
    if (jwt) {
      dispatch(getUser(auth.jwt || jwt));
      dispatch(getOrder(auth.jwt || jwt));
      dispatch(getCart(auth.jwt || jwt));
    }
  }, [dispatch, jwt, auth.jwt]);

  return (
    <div className="App">
      <PrimeReactProvider>
        <AuthProvider>
          <HistoryRouter
            history={history}
            future={{ v7_relativeSplatPath: true }}
          >
            <ScrollToTop />
            <Routes>
              {/* Routes with Layout (NavBar + Footer) */}
              <Route
                path="/*"
                element={
                  <Layout noLayout={false}>
                    <Routes>
                      <Route path="/" element={<HomePage />} />
                      {AuthRoutes()}
                      {ForgotPasswordRoutes()}
                      {ShopRoutes()}
                      {InfoRoutes()}
                      {DashboardRoutes()}
                    </Routes>
                  </Layout>
                }
              />

              {/* PageNotFound (Without Layout) */}
              <Route
                path="*"
                element={
                  <Layout noLayout={true}>
                    <PageNotFound />
                  </Layout>
                }
              />
            </Routes>
          </HistoryRouter>
        </AuthProvider>
      </PrimeReactProvider>
    </div>
  );
}

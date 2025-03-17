import { Navigate, Route } from "react-router-dom";
import Dashboard from "../Pages/Dashboard/Dashboard";
import Order from "../Pages/Dashboard/Order/Order";
import ErrorMessageAlert from "../Components/Alerts/ErrorMessageAlert";
import { useEffect } from "react"; // Import useEffect
import PaymentSuccess from "../Pages/Dashboard/Order/PaymentSuccess";
import PaymentFailure from "../Pages/Dashboard/Order/PaymentFailure";

const AuthenticatedRoute = ({ children }) => {
  const jwt = localStorage.getItem("jwt");

  // Trigger the alert only when not authenticated
  useEffect(() => {
    if (!jwt) {
      ErrorMessageAlert({ message: "LogIn First" });
    }
  }, [jwt]);

  if (jwt) {
    return children;
  }

  // Redirect user if not authenticated
  return <Navigate to="/Login" />;
};

const DashboardRoutes = () => [
  <Route
    key="dashboard"
    path="/Dashboard"
    element={
      <AuthenticatedRoute>
        <Dashboard />
      </AuthenticatedRoute>
    }
  />,
  <Route
    key="payment-success"
    path="/payment/success"
    element={
      <AuthenticatedRoute>
        <PaymentSuccess />
      </AuthenticatedRoute>
    }
  />,
  <Route
    key="payment-failure"
    path="/payment/failure"
    element={
      <AuthenticatedRoute>
        <PaymentFailure />
      </AuthenticatedRoute>
    }
  />,
  <Route
    key="order"
    path="/Order"
    element={
      <AuthenticatedRoute>
        <Order />
      </AuthenticatedRoute>
    }
  />,
];

export default DashboardRoutes;

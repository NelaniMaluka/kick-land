import { Navigate, Route } from "react-router-dom";
import Dashboard from "../Pages/Dashboard/Dashboard";
import Order from "../Pages/Dashboard/Order/Order";
import ErrorMessageAlert from "../Components/Alerts/ErrorMessageAlert";
import { useSelector } from "react-redux";
import { useEffect } from "react"; // Import useEffect

const AuthenticatedRoute = ({ children }) => {
  const auth = useSelector((state) => state.auth);

  // Trigger the alert only when not authenticated
  useEffect(() => {
    if (!auth.authenticated) {
      ErrorMessageAlert({ message: "LogIn First" });
    }
  }, [auth.authenticated]);

  if (auth.authenticated) {
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
        <Dashboard />
      </AuthenticatedRoute>
    }
  />,
  <Route
    key="payment-failure"
    path="/payment/failure"
    element={
      <AuthenticatedRoute>
        <Dashboard />
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

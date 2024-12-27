import { Navigate, Route } from "react-router-dom";
import Dashboard from "../Pages/Dashboard/Dashboard";
import Order from "../Pages/Dashboard/Order/Order";
import { useAuth } from "../Context/AuthContext";
import ErrorMessageAlert from "../Components/Alerts/ErrorMessageAlert";

function AuthenticatedRoute({ children }) {
  const authContext = useAuth();
  if (authContext.isAuthenticated) {
    return children;
  }
  ErrorMessageAlert({ message: "LogIn First" });
  return <Navigate to="/Login" />;
}

const DashboardRoutes = () => [
  <Route
    path="/Dashboard"
    element={
      <AuthenticatedRoute>
        <Dashboard />
      </AuthenticatedRoute>
    }
  />,
  <Route
    path="/Dashboard/success"
    element={
      <AuthenticatedRoute>
        <Dashboard />
      </AuthenticatedRoute>
    }
  />,
  <Route
    path="/Dashboard/cancel"
    element={
      <AuthenticatedRoute>
        <Dashboard />
      </AuthenticatedRoute>
    }
  />,
  <Route
    path="/Order"
    element={
      <AuthenticatedRoute>
        <Order />
      </AuthenticatedRoute>
    }
  />,
];
export default DashboardRoutes;

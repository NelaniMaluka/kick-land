import { Navigate, Route } from "react-router-dom";
import Dashboard from "../Pages/Dashboard/Dashboard";
import Order from "../Pages/Dashboard/Order/Order";
import ErrorMessageAlert from "../Components/Alerts/ErrorMessageAlert";
import { useSelector } from "react-redux";

function AuthenticatedRoute({ children }) {
  const { auth } = useSelector((store) => store);
  if (auth.authenticated) {
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

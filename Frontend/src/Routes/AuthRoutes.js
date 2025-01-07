import { Route } from "react-router-dom";
import LoginForm from "../Pages/LogIn/LogInForm";
import CreateAccount from "../Pages/CreateAccount/CreateAccount";
import ForgotPassword from "../Pages/ForgotPassword/ForgotPassword";

// Return Route components directly with keys
const AuthRoutes = () => [
  <Route key="login" path="/Login" element={<LoginForm />} />,
  <Route
    key="create-account"
    path="/Create-Account"
    element={<CreateAccount />}
  />,
  <Route
    key="forgot-password"
    path="/Forgot-Password"
    element={<ForgotPassword />}
  />,
];

export default AuthRoutes;

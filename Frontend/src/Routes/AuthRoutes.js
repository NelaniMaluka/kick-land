import { Route } from "react-router-dom";
import LoginForm from "../Pages/LogIn/LogInForm";
import CreateAccount from "../Pages/CreateAccount/CreateAccount";
import ForgotPassword from "../Pages/ForgotPassword/ForgotPassword";

// Return Route components directly
const AuthRoutes = () => [
  <Route path="/Login" element={<LoginForm />} />,
  <Route path="/Create-Account" element={<CreateAccount />} />,
  <Route path="/Forgot-Password" element={<ForgotPassword />} />,
];

export default AuthRoutes;

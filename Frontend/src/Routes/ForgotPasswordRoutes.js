import { Route } from "react-router-dom";
import ForgotPassword from "../Pages/ForgotPassword/ForgotPassword";
import VerifyOtp from "../Pages/ForgotPassword/VerifyOtp";
import ChangePassword from "../Pages/ForgotPassword/ChangePassword";

const ForgotPasswordRoutes = () => [
  <Route
    key="forgot-password"
    path="/Forgot-Password"
    element={<ForgotPassword />}
  />,
  <Route key="verify-otp" path="/Verify-Otp" element={<VerifyOtp />} />,
  <Route
    key="change-password"
    path="/Change-Password"
    element={<ChangePassword />}
  />,
];

export default ForgotPasswordRoutes;

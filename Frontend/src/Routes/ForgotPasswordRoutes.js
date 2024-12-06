import { Route } from "react-router-dom";
import ForgotPassword from "../Pages/ForgotPassword/ForgotPassword";
import VerifyOtp from "../Pages/ForgotPassword/VerifyOtp";
import ChangePassword from "../Pages/ForgotPassword/ChangePassword";

const ForgotPasswordRoutes = () => [
  <Route path="/Forgot-Password" element={<ForgotPassword />} />,
  <Route path="/Verify-Otp" element={<VerifyOtp />} />,
  <Route path="/Change-Password" element={<ChangePassword />} />,
];

export default ForgotPasswordRoutes;

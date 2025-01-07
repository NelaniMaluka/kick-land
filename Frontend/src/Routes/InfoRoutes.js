import { Route } from "react-router-dom";
import AboutUs from "../Pages/AboutUs/About-Us";
import TermsOfService from "../Pages/TermsOfService/TermsOfService";
import RefundPolicy from "../Pages/RefundPolicy/RefundPolicy";
import ContactUs from "../Pages/ContactUs/ContactUs";

// Return Route components directly
const InfoRoutes = () => [
  <Route key="about-us" path="/Info/About-Us" element={<AboutUs />} />,
  <Route
    key="terms-of-service"
    path="/Info/Terms-of-Service"
    element={<TermsOfService />}
  />,
  <Route
    key="refund-policy"
    path="/Info/Refund-Policy"
    element={<RefundPolicy />}
  />,
  <Route key="help" path="/Info/Help" element={<ContactUs />} />,
];

export default InfoRoutes;

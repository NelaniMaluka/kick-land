import { Route } from "react-router-dom";
import AboutUs from "../Pages/AboutUs/About-Us";
import TermsOfService from "../Pages/TermsOfService/TermsOfService";
import RefundPolicy from "../Pages/RefundPolicy/RefundPolicy";
import ContactUs from "../Pages/ContactUs/ContactUs";

// Return Route components directly
const InfoRoutes = () => [
  <Route path="/Info/About-Us" element={<AboutUs />} />,
  <Route path="/Info/Terms-of-Service" element={<TermsOfService />} />,
  <Route path="/Info/Refund-Policy" element={<RefundPolicy />} />,
  <Route path="/Info/Help" element={<ContactUs />} />,
];

export default InfoRoutes;

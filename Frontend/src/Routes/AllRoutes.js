import { Route } from "react-router-dom";
import AuthRoutes from "./AuthRoutes";
import ShopRoutes from "./ShopRoutes";
import InfoRoutes from "./InfoRoutes";
import DashboardRoutes from "./DashboardRoutes";
import HomePage from "../Pages/Home/HomePage";

// Simply return route components
const AllRoutes = () => {
  return (
    <>
      <Route path="/" element={<HomePage />} />
      <AuthRoutes />
      <ShopRoutes />
      <InfoRoutes />
      <DashboardRoutes />
    </>
  );
};

export default AllRoutes;

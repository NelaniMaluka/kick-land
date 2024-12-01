import { Route } from "react-router-dom";
import ShopByCategory from "../Pages/Shop/ShopByCategory";
import ProductDetails from "../Components/ProductDetails/ProductDetails";
import ShopAll from "../Pages/Shop/ShopAll";

const ShopRoutes = () => [
  <Route key="shop-item" path="/shop/:item" element={<ShopByCategory />} />,
  <Route
    key="product-details"
    path="/shop/:category/:productName"
    element={<ProductDetails />}
  />,
  <Route key="shop-all" path="/shop/shop-all" element={<ShopAll />} />,
];

export default ShopRoutes;

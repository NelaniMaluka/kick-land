import { useAuth } from "../../Security/AuthContext";
import ShopAllTopBanner from "./ShopAllTopBanner";

function ShopAll() {
  const useContext = useAuth();
  const products = useContext.isProducts;

  return (
    <>
      <ShopAllTopBanner products={products} />
    </>
  );
}

export default ShopAll;

import { useAuth } from "../../Security/AuthContext";
import ProductBanner from "../ProductBanner";
import ProductCard from "../ProductCard";

function ShopAll() {
  const useContext = useAuth();
  const products = useContext.isProducts;

  return (
    <>
      <ProductBanner products={products} />
      <ProductCard products={products} />
    </>
  );
}

export default ShopAll;

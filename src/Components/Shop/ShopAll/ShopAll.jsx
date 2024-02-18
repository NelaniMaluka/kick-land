import { useAuth } from "../../Security/AuthContext";
import ProductBanner from "../ProductBanner";
import ProductCard from "../ProductCard";
import { useState } from "react";
import FooterBanner from "../../Footer/FooterBanner";

function ShopAll() {
  const useContext = useAuth();
  const [isProducts, setProducts] = useState(useContext.isProducts || []);

  return (
    <>
      <ProductBanner products={isProducts} setProducts={setProducts} />
      <ProductCard products={isProducts} />
      <FooterBanner />
    </>
  );
}

export default ShopAll;

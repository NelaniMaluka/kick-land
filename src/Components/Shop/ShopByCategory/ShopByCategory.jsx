import { useAuth } from "../../Security/AuthContext";
import ProductBanner from "../ProductBanner";
import ProductCard from "../ProductCard";
import { useParams } from "react-router-dom";
import { useState } from "react";
import FooterBanner from "../../Footer/FooterBanner";

function ShopByCategory() {
  const useContext = useAuth();
  const [isProducts, setProducts] = useState(useContext.isProducts || []);
  const category = useParams();

  const filteredProducts = isProducts.filter(
    (product) => product.category === category.item
  );

  return (
    <>
      <ProductBanner products={filteredProducts} setProducts={setProducts} />
      <ProductCard products={filteredProducts} />
      <FooterBanner />
    </>
  );
}

export default ShopByCategory;

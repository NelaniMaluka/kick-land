import { useAuth } from "../../Security/AuthContext";
import ProductBanner from "../ProductBanner";
import ProductCard from "../ProductCard";
import { useParams } from "react-router-dom";

function ShopByCategory() {
  const useContext = useAuth();
  const products = useContext.isProducts;
  const category = useParams();

  const filteredProducts = products.filter(
    (product) => product.category === category.item
  );

  return (
    <>
      <ProductBanner products={filteredProducts} />
      <ProductCard products={filteredProducts} />
    </>
  );
}

export default ShopByCategory;

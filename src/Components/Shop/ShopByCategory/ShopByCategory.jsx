import { useAuth } from "../../Security/AuthContext";
import ProductBanner from "../ProductBanner";
import ProductCard from "../ProductCard";
import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import FooterBanner from "../../Footer/FooterBanner";

function ShopByCategory() {
  const authContext = useAuth();
  const [isProducts, setProducts] = useState(authContext.isProducts || []);
  const [filteredProducts, setFilteredProducts] = useState([]);
  const category = useParams();

  useEffect(() => {
    const fetchData = async () => {
      try {
        await authContext.retrieveProducts();
        if (isProducts.length === 0) {
          setProducts(authContext.isProducts || []);
        }
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    fetchData();
  }, [authContext, isProducts.length]);

  useEffect(() => {
    // Move setFilteredProducts inside the useEffect block
    setFilteredProducts(
      isProducts.filter((product) => product.category === category.item)
    );
  }, [isProducts, category]);

  return (
    <>
      <ProductBanner
        products={filteredProducts}
        setFilteredProducts={setFilteredProducts}
      />
      <ProductCard products={filteredProducts} />
      <FooterBanner />
    </>
  );
}

export default ShopByCategory;

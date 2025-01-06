import { useAuth } from "../../Context/AuthContext";
import ShopProductBanner from "../../Components/Banners/ShopProductBanner";
import ShopProductsCard from "../../Components/Cards/ShopProductsCard";
import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import AssuranceBanner from "../../Components/Banners/AssuranceBanner";

function ShopByCategory() {
  const authContext = useAuth();
  const [isProducts, setProducts] = useState(authContext.isProducts || []);
  const [filteredProducts, setFilteredProducts] = useState([]);
  const category = useParams();

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (isProducts.length === 0) {
          await authContext.retrieveProducts();
          setProducts(authContext.isProducts || []);
        }
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    fetchData();
  }, [authContext, isProducts.length]);

  useEffect(() => {
    setFilteredProducts(
      isProducts.filter((product) => product.category === category.item)
    );
  }, [isProducts, category]);

  return (
    <>
      <ShopProductBanner
        products={filteredProducts}
        setFilteredProducts={setFilteredProducts}
      />
      <ShopProductsCard products={filteredProducts} />
      <AssuranceBanner />
    </>
  );
}

export default ShopByCategory;

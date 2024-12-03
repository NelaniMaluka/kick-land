import { useAuth } from "../../Context/AuthContext";
import ShopProductBanner from "../../Components/Banners/ShopProductBanner";
import ShopProductsCard from "../../Components/Cards/ShopProductsCard";
import { useState, useEffect } from "react";
import AssuranceBanner from "../../Components/Banners/AssuranceBanner";

function ShopAll() {
  const authContext = useAuth();
  const [isProducts, setProducts] = useState(authContext.isProducts || []);

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

  return (
    <>
      <ShopProductBanner
        products={isProducts}
        setFilteredProducts={setProducts}
      />
      <ShopProductsCard products={isProducts} />
      <AssuranceBanner />
    </>
  );
}

export default ShopAll;

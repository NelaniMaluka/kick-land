import { useAuth } from "../../Context/AuthContext";
import ShopProductBanner from "../../Components/Banners/ShopProductBanner";
import ShopProductsCard from "../../Components/Cards/ShopProductsCard";
import { useState } from "react";
import AssuranceBanner from "../../Components/Banners/AssuranceBanner";

function ShopAll() {
  const useContext = useAuth();
  const [isProducts, setProducts] = useState(useContext.isProducts || []);

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

import { useAuth } from "../../Context/AuthContext";
import ShopProductBanner from "../../Components/Banners/ShopProductBanner";
import ShopProductsCard from "../../Components/Cards/ShopProductsCard";
import { useState, useEffect } from "react";
import AssuranceBanner from "../../Components/Banners/AssuranceBanner";
import ErrorMessageAlert from "../../Components/Alerts/ErrorMessageAlert";
import CircularIndeterminate from "../../Utils/LoadingSpinner";

export default function ShopAll() {
  const authContext = useAuth();
  const [loading, setLoading] = useState(true);
  const [isProducts, setProducts] = useState(authContext.isProducts || []);

  // Fetsches Product data on page load
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

  // Handles loading screen
  useEffect(() => {
    if (!isProducts || isProducts.length === 0) {
      setLoading(true);
      return;
    }

    try {
      if (isProducts || isProducts.length >= 1) {
        setLoading(false);
        return;
      }
    } catch (err) {
      ErrorMessageAlert({
        message:
          "We're sorry, something went wrong while loading products. Please try refreshing the page or check back later.",
      });
      setLoading(false);
    }
  }, [isProducts]);

  // Returs the loading screen
  if (loading) return <CircularIndeterminate />;

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

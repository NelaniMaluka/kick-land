import HeroSection from "../../Components/HeroSection/HeroSection";
import AssuranceBanner from "../../Components/Banners/AssuranceBanner";
import { useAuth } from "../../Context/AuthContext";
import ProductCategoryCarousel from "../../Components/Couresels/ProductCategoryCarousel";
import ImmageCattegoryBanner from "../../Components/Banners/ImmageCattegoryBanner";

function HomePage() {
  const useContext = useAuth();
  const products = useContext.isProducts;

  return (
    <div>
      <HeroSection />
      <ProductCategoryCarousel products={products} category="Dunk" />
      <ImmageCattegoryBanner />
      <ProductCategoryCarousel products={products} category="AirMax" />
      <AssuranceBanner />
    </div>
  );
}

export default HomePage;

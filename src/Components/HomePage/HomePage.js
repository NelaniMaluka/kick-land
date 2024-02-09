import HeroSection from "./HeroSection";
import FooterBanner from "../Footer/FooterBanner";
import { useAuth } from "../Security/AuthContext";
import HomeCarousel from "./Couresel";

function HomePage() {
  const useContext = useAuth();
  const products = useContext.isProducts;

  return (
    <div>
      <HeroSection />
      <HomeCarousel products={products} category="Dunk" />

      <HomeCarousel products={products} category="AirMax" />
      <FooterBanner />
    </div>
  );
}

export default HomePage;

import HeroSection from "./HeroSection";
import FooterBanner from "../Footer/FooterBanner";
import { useAuth } from "../Security/AuthContext";
import HomeCarousel from "./Couresel";
import BannerCard from "./BannerCard";

function HomePage() {
  const useContext = useAuth();
  const products = useContext.isProducts;

  return (
    <div>
      <HeroSection />
      <HomeCarousel products={products} category="Dunk" />
      <BannerCard />
      <HomeCarousel products={products} category="AirMax" />
      <FooterBanner />
    </div>
  );
}

export default HomePage;

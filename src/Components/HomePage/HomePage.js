import HomePageView1 from "./HomePageView1";
import FooterBanner from "../Footer/FooterBanner";

function HomePage() {
  return (
    <div>
      <HomePageView1 item="Dunk" />
      <HomePageView1 item="Air-Force" />
      <FooterBanner />
    </div>
  );
}

export default HomePage;
